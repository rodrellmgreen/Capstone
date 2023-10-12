package com.techelevator.controller;


import com.techelevator.dao.FoodDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.model.Food;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RequestMapping( path="/food")
@RestController
@CrossOrigin
public class FoodController {

    @Autowired
    private FoodDao foodDao;

    @Autowired
    private ProfileDao profileDao;


    @GetMapping()
    public List<Food> getAllFoodByUsername(Principal principal){
        return foodDao.getFoodByIdAndUsername(principal.getName());
    }

    @GetMapping(path="/{id}")
    public Food getSpecificFood(@PathVariable int id){
        return foodDao.getSpecificFood(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createFood(Principal principal,@RequestBody Food food){
        int userId= profileDao.getUserIdByUsername(principal.getName());
        food.setUserId(userId);
        foodDao.createFood(food);
    }

    @PutMapping(path="/{id}")
    public void updateFood(Principal principal, @RequestBody Food food, @PathVariable int id){
        int userId= profileDao.getUserIdByUsername(principal.getName());
        food.setUserId(userId);
        food.setFoodId(id);
        boolean updated= foodDao.updateFood(food);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not updated");
        }
    }
    @DeleteMapping(path="/{id}")
    public void deleteFood(@PathVariable int id){
        boolean deleted= foodDao.deleteFood(id);
        if(!deleted){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Food not deleted");
        }
    }

    @GetMapping(path="/quickAdd")
    public List<Food> getQuickAddFoods(Principal principal) {
        return foodDao.getQuickAddFoods(principal.getName());
    }

    @GetMapping(path="/today")
    public List<Food> getTodaysFoodByDateAndUserName(Principal principal){
        return foodDao.getTodaysFood(principal.getName());
    }

}
