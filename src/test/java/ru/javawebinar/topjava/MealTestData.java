package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealTestData {
    public static final int MEAL_START_ID = 100003;
    public static final int ADMIN_MEAL_START_ID = 100009;
    public static final Meal MEAL1 = new Meal(MEAL_START_ID, LocalDateTime.of(2022, Month.JUNE, 13, 10, 0), "завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL_START_ID + 1, LocalDateTime.of(2022, Month.JUNE, 13, 16, 0), "обед", 500);
    public static final Meal MEAL3 = new Meal(MEAL_START_ID + 2, LocalDateTime.of(2022, Month.JUNE, 13, 20, 0), "ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL_START_ID + 3, LocalDateTime.of(2022, Month.JUNE, 14, 10, 0), "завтрак", 500);
    public static final Meal MEAL5 = new Meal(MEAL_START_ID + 4, LocalDateTime.of(2022, Month.JUNE, 14, 16, 0), "обед", 500);
    public static final Meal MEAL6 = new Meal(MEAL_START_ID + 5, LocalDateTime.of(2022, Month.JUNE, 14, 20, 0), "ужин", 500);
    public static final Meal ADMIN_MEAL1 = new Meal(ADMIN_MEAL_START_ID, LocalDateTime.of(2022, Month.JUNE, 14, 16, 0), "обед админа", 500);
    public static final Meal ADMIN_MEAL2 = new Meal(ADMIN_MEAL_START_ID + 1, LocalDateTime.of(2022, Month.JUNE, 14, 20, 0), "admin`s supper", 500);
    public static final List<Meal> MEALS = Arrays.asList(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6, ADMIN_MEAL1, ADMIN_MEAL2);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.JUNE, 13, 12, 0), "new meal", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL1);
        updated.setId(MEAL_START_ID);
        updated.setDescription("updated meal");
        updated.setCalories(555);
        return updated;
    }

}
