package com.techelevator.dao;

import com.techelevator.model.Food;

import java.util.List;

public interface FoodDao {

    int getFoodIdByUsername(String userName);

    List<Food> getFoodByIdAndUsername(String userName);

    void createFood(Food food);

    boolean updateFood(Food food);

    boolean deleteFood(int id);

    List<Food> listAllFoods();

    int getUserIdByUsername(String username);

    Food getSpecificFood(int id);

    List<Food> getQuickAddFoods(String username);

    List<Food> getTodaysFood(String userName);


}
