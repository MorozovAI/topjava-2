package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealRepository repository = new InMemoryMealRepositoryImpl();

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new InMemoryMealRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id), LocalDateTime.parse(req.getParameter("dateTime")), req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));
        log.info(id.isEmpty() ? "Create {}" : "Update {}", meal);
        repository.add(meal);
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equalsIgnoreCase("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                log.info("Delete {}", id);
                repository.delete(id);
                request.setAttribute("meals", MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("update")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Meal meal = repository.get(id);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("insert")) {
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
            }
        } else {
            log.info("Get all");
            request.setAttribute("meals", MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }
}

