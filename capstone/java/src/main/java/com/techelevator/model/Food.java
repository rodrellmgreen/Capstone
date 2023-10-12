package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.util.Date;

public class Food {


    @Min(0)
    private int foodId;
    @NotEmpty
    private String type;
    @NotEmpty
    private double calories;
    @NotEmpty
    private double carbs;
    @NotEmpty
    private double protein;
    @NotEmpty
    private double fats;
    @NotEmpty
    private double fiber;
    @NotEmpty
    private double servingSize;
    //@NotEmpty
    private boolean quickAdd = false;
    @Min(0)
    private int userId;
//    @NotEmpty
    @JsonFormat(locale = "zh", timezone = "EST", pattern="yyyy-MM-dd")
    private Date date;

    public Food() {

    }

    public Food(String type, double calories, double carbs, double protein, double fats, double fiber, double servingSize, boolean quickAdd, int userId, Date date) {
        this.type = type;
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fats = fats;
        this.fiber = fiber;
        this.servingSize = servingSize;
        //this.quickAdd = quickAdd;
        this.userId = userId;
        this.date = date;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public void setServingSize(double servingSize) {
        this.servingSize = servingSize;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getType() {
        return type;
    }

    public double getCalories() {
        return calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getProtein() {
        return protein;
    }

    public double getFats() {
        return fats;
    }

    public double getFiber() {
        return fiber;
    }

    public double getServingSize() {
        return servingSize;
    }

    public boolean getQuickAdd() {
        return this.quickAdd;
    }

    public void setQuickAdd(boolean quickAdd) {
        this.quickAdd = quickAdd;
    }
}
