package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void Get() {
        Meal actual = service.get(100009, 100001);
        assertThat(actual).usingRecursiveComparison().isEqualTo(ADMIN_MEAL1);
    }

    @Test
    public void GetAlien() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_START_ID, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL_START_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_START_ID, USER_ID));
    }

    @Test
    public void deleteAlien() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_START_ID, ADMIN_ID));
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertThat(updated).usingRecursiveComparison().isEqualTo(service.get(MEAL_START_ID, USER_ID));
    }

    @Test
    public void updateAlien() {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertThat(created).usingRecursiveComparison().isEqualTo(newMeal);
        assertThat(service.get(newId, USER_ID)).usingRecursiveComparison().isEqualTo(newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, LocalDateTime.of(2022, Month.JUNE, 13, 10, 0),
                        "завтрак", 1000), USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> expected = Arrays.asList(MEAL3, MEAL2, MEAL1);
        assertThat(expected).usingRecursiveComparison().isEqualTo(service.getBetweenInclusive(LocalDate.of(2022, Month.JUNE, 13),
                LocalDate.of(2022, Month.JUNE, 13), USER_ID));
    }
}