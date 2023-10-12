package com.techelevator.model;

import java.util.List;

public class Meal {

    private int mealId;
    private int userId;
    private String mealType;

    public Meal() {}

    public Meal(int userId, String mealType) {
        this.userId = userId;
        this.mealType = mealType;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

}
