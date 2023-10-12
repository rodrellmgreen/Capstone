package com.techelevator.controller;

import com.techelevator.dao.MealDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Food;
import com.techelevator.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping(path = "/meals")
@RestController
@CrossOrigin
public class MealController {

    @Autowired
    MealDao mealDao;

    @Autowired
    UserDao userDao;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new-meal")
    public void createMeal(Principal principal, @RequestBody Meal meal) {
        String userName = principal.getName();
        int userId = userDao.findIdByUsername(userName);
        mealDao.createMeal(userId, meal.getMealType());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{mealId}")
    public void addFoodToMeal(@PathVariable int mealId, @RequestBody Food food) {
        mealDao.addFoodsToMeal(mealId, food);
    }

    @GetMapping()
    public List<Meal> getMeals(Principal principal) {
        String userName = principal.getName();
        int userId = userDao.findIdByUsername(userName);
        return mealDao.getMeals(userId);
    }

    @GetMapping("/{mealId}")
    public List<Food> getFoods(@PathVariable int mealId, Principal principal) {
//        String userName = principal.getName();
//        int userId = userDao.findIdByUsername(userName);
        return mealDao.getFoods(mealId);
    }

    @PutMapping()
    public boolean updateMealType(@RequestBody Meal meal) {
        return mealDao.updateMealType(meal);
    }

    @PutMapping("/{id}")
    public boolean updateMealFood(@PathVariable int foodId, @RequestBody Meal meal) {
        int mealId = meal.getMealId();
        return mealDao.updateMealFood(mealId, foodId);
    }

    @DeleteMapping("/{mealId}")
    public void deleteMeal(@PathVariable int mealId) {
        mealDao.deleteMeal(mealId);
    }

}
