package ru.javawebinar.topjava;

import org.assertj.core.api.Assertions;
import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealTestData {
    public static final int MEAL_START_ID = 100003;
    public static final int ADMIN_MEAL_START_ID = 100010;
    public static final Meal meal1 = new Meal(MEAL_START_ID, LocalDateTime.of(2022, Month.JUNE, 13, 10, 0), "завтрак", 605);
    public static final Meal meal2 = new Meal(MEAL_START_ID + 1, LocalDateTime.of(2022, Month.JUNE, 13, 16, 0), "обед", 700);
    public static final Meal meal3 = new Meal(MEAL_START_ID + 2, LocalDateTime.of(2022, Month.JUNE, 13, 20, 0), "ужин", 700);
    public static final Meal meal4 = new Meal(MEAL_START_ID + 3, LocalDateTime.of(2022, Month.JUNE, 14, 10, 0), "завтрак", 500);
    public static final Meal meal5 = new Meal(MEAL_START_ID + 4, LocalDateTime.of(2022, Month.JUNE, 14, 16, 0), "обед", 700);
    public static final Meal meal6 = new Meal(MEAL_START_ID + 5, LocalDateTime.of(2022, Month.JUNE, 14, 20, 0), "ужин", 700);
    public static final Meal meal7 = new Meal(MEAL_START_ID + 6, LocalDateTime.of(2022, Month.JUNE, 21, 0, 0), "граничное значение", 605);
    public static final Meal adminMeal1 = new Meal(ADMIN_MEAL_START_ID, LocalDateTime.of(2022, Month.JUNE, 14, 16, 0), "обед админа", 500);
    public static final Meal adminMeal2 = new Meal(ADMIN_MEAL_START_ID + 1, LocalDateTime.of(2022, Month.JUNE, 14, 20, 0), "admin`s supper", 500);
    public static final List<Meal> meals = Arrays.asList(meal7, meal6, meal5, meal4, meal3, meal2, meal1);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.JUNE, 13, 12, 0), "new meal", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1);
        updated.setId(MEAL_START_ID);
        updated.setDescription("updated meal");
        updated.setCalories(555);
        return updated;
    }
    public static void assertMatch(Object actual, Object expected) {
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
