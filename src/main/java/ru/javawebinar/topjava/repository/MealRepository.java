package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {

    void add(Meal meal);

    void delete(int id);

    Collection<Meal> getAll();

    Meal get(int id);


}
