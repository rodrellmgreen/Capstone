package com.techelevator.controller;

import com.techelevator.Twilio.TwilioService;
import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Profile;
import com.techelevator.model.TimeStampDTO;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@CrossOrigin
@RequestMapping(path="/profile")
@PreAuthorize("isAuthenticated()")
@RestController
public class ProfileController {
    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private UserDao userDao;

    @GetMapping()
    public Profile getProfileByUsername(Principal principal){
        return profileDao.findProfileByUsername(principal.getName());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Profile createProfile(Principal principal, @Valid @RequestBody Profile profile){
        int userID=profileDao.getUserIdByUsername(principal.getName());
        profile.setUserId(userID);

        return profileDao.createProfile(profile);
    }

    @DeleteMapping
    public void deleteProfile(Principal principal){
        boolean deleted=profileDao.deleteProfile(principal.getName());
        if(!deleted){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Profile Not Deleted");
        }
    }

    @PutMapping()
    public void updateProfile(Principal principal,  @Valid @RequestBody Profile profile){
        int profileId=profileDao.getProfileIdByUsername(principal.getName());
        int userId=profileDao.getUserIdByUsername(principal.getName());
        profile.setProfileId(profileId);
        profile.setUserId(userId);
        boolean updated=profileDao.updateProfile(profile);

       if(!updated){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Profile Not Updated");
        }
    }

    @PutMapping(path = "/streak")
    public void updateStreak(Principal principal,@RequestBody User user){
        int userId= userDao.findIdByUsername(principal.getName());
        User oldUser=userDao.getUserById(userId);
        user.setId(oldUser.getId());
        user.setAuthorities(oldUser.getAuthorities());
        user.setUsername(oldUser.getUsername());
        user.setPassword(oldUser.getPassword());
        boolean updated=userDao.updateStreak(user);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Streak Not Updated");
        }
    }


//    @GetMapping(path = "/sms")
//    public void verifyUserNumber(Principal principal){
//        String userPhoneNumber = "";
////        int userID=profileDao.getUserIdByUsername(principal.getName());
//        userPhoneNumber = profileDao.getUserPhoneNumber(principal.getName());
//        userPhoneNumber = "+1" + userPhoneNumber;
////        userPhoneNumber = "+13307143204";
//        TwilioService twilio = new TwilioService();
//
//        twilio.verifyUser(userPhoneNumber);
//
//    }

    @PutMapping(path = "/sms")
    public void smsAlert(Principal principal, @RequestBody TimeStampDTO timeStampDTO){

        String userPhoneNUmber = "";

        userPhoneNUmber = profileDao.getUserPhoneNumber(principal.getName());
        userPhoneNUmber = "+1" + userPhoneNUmber;

        TwilioService twilio = new TwilioService();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlus15 = now.plusMinutes(15);
//        TimeStampDTO currentTime = new TimeStampDTO();
        if (timeStampDTO.getTimeStamp().isAfter(nowPlus15)){



            twilio.sendMessageAtTime(userPhoneNUmber,timeStampDTO.getTimeStamp() );
            System.out.println(timeStampDTO.getTimeStamp());
        } else {

            twilio.sendMessage(userPhoneNUmber);
            System.out.println(timeStampDTO.getTimeStamp());
        }


    }






}
