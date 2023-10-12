package com.techelevator.dao;

import com.techelevator.model.Food;
import com.techelevator.model.Meal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMealDao implements MealDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcMealDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createMeal(int userId, String mealType) {
        String sql = "INSERT INTO meal (user_id, meal_type) " +
                "VALUES (?, ?) RETURNING meal_id;";
        Integer mealId = jdbcTemplate.queryForObject(sql, Integer.class, userId, mealType);
    }

    @Override
    public void addFoodsToMeal(int mealId, Food food) {
        String sql = "INSERT INTO meal_food (meal_id, food_name, calories, protein, carbs, fiber, fats, serving_size) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, mealId, food.getType(), food.getCalories(), food.getProtein(), food.getCarbs(), food.getFiber(), food.getFats(), food.getServingSize());
    }

    @Override
    public List<Meal> getMeals(int userId) {
        List<Meal> meals = new ArrayList<>();
        String sql = "SELECT * FROM meal WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            meals.add(mapRowToMeal(results));
        }
        return meals;
    }

    @Override
    public boolean updateMealType(Meal meal) {
        boolean success = false;
        String sql = "UPDATE meal SET meal_type = ? WHERE meal_id = ?";
        int linesUpdated = jdbcTemplate.update(sql, meal.getMealType(), meal.getMealId());
        if(linesUpdated == 1) {
            success = true;
        }
        return success;
    }

    @Override
    public boolean updateMealFood(int mealId, int foodId) {
        boolean success = false;
        String sql = "UPDATE meal_food SET food_id = ? WHERE meal_id = ?";
        int linesUpdated = jdbcTemplate.update(sql, foodId, mealId);
        if(linesUpdated == 1) {
            success = true;
        }
        return success;
    }

    @Override
    public void deleteMeal(int mealId) {
        String sql2 = "DELETE FROM meal_food WHERE meal_id = ?;";
        int linesUpdated2 = jdbcTemplate.update(sql2, mealId);
        String sql = "DELETE FROM meal WHERE meal_id = ?;";
        int linesUpdated = jdbcTemplate.update(sql, mealId);
    }

    @Override
    public List<Food> getFoods(int mealId) {
        List<Food> foods = new ArrayList<>();
        String sql = "SELECT * FROM meal_food WHERE meal_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealId);
        while(results.next()) {
            foods.add(mapRowToFood(results));
        }
        return foods;
    }

    public static Meal mapRowToMeal(SqlRowSet results) {
        Meal meal = new Meal();
        meal.setMealId(results.getInt("meal_id"));
        meal.setUserId(results.getInt("user_id"));
        meal.setMealType(results.getString("meal_type"));
        return meal;
    }

    private Food mapRowToFood(SqlRowSet results) {
        Food food = new Food();
        //food.setFoodId(0);
        food.setUserId(0);
        food.setType(results.getString("food_name"));
        food.setCalories(results.getDouble("calories"));
        food.setCarbs(results.getDouble("carbs"));
        food.setProtein(results.getDouble("protein"));
        food.setFats(results.getDouble("fats"));
        food.setFiber(results.getDouble("fiber"));
        food.setServingSize(results.getDouble("serving_size"));
        //food.setQuickAdd(false);
        //food.setDate(results.getDate("date_entered"));
        return food;
    }

}
