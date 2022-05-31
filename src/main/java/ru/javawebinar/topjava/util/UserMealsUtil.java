package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
        System.out.println();
        mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(19, 0), 2000);
        mealsTo.forEach(System.out::println);
        // System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(23, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles

        Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();
        meals.forEach(meal -> caloriesSumPerDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));
        List<UserMealWithExcess> listWithExcess = new ArrayList<>();
        meals.forEach(meal -> {
            if (TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime)) {
                listWithExcess.add(createMealWithExcess(meal, caloriesSumPerDate.get(meal.getDate()) > caloriesPerDay));
            }
        });
        return listWithExcess;

    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime
            startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        Map<LocalDate, Integer> caloriesSumPerDate = meals.stream().collect(Collectors.toMap(UserMeal::getDate, UserMeal::getCalories, Integer::sum));

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createMealWithExcess(meal, caloriesSumPerDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static UserMealWithExcess createMealWithExcess(UserMeal meal, Boolean isExceeded) {
        return new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isExceeded);
    }
}
