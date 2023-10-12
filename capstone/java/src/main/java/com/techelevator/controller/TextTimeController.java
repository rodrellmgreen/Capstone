//package com.techelevator.controller;
//
//import com.techelevator.Twilio.TwilioService;
//import com.techelevator.dao.ProfileDao;
//import com.techelevator.dao.TextTimeDao;
//import com.techelevator.dao.UserDao;
//import com.techelevator.model.TextTime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.security.Principal;
//
//@RequestMapping(path="/text")
//@PreAuthorize("isAuthenticated()")
//@CrossOrigin
//@RestController
//public class TextTimeController {
//
//    @Autowired
//    private TextTimeDao textTimeDao;
//
//    @Autowired
//    private ProfileDao profileDao;
//
//    @Autowired
//    private UserDao userDao;
//
//    @GetMapping
//    public TextTime getTime(Principal principal){
//        int userId = userDao.findIdByUsername(principal.getName());
//        return textTimeDao.getTime(userId);
//    }
//
//    @PostMapping
//    public void createTime(Principal principal, @RequestBody TextTime textTime){
//        int userId = userDao.findIdByUsername(principal.getName());
//        textTime.setUserId(userId);
//        textTimeDao.createTime(textTime);
//    }
//
//    @PutMapping
//    public void updateTime(Principal principal, @RequestBody TextTime textTime){
//        int userId = userDao.findIdByUsername(principal.getName());
//        textTime.setUserId(userId);
//        boolean updated = textTimeDao.updateTime( textTime );
//        if (!updated){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time not Updated");
//        }
//    }
//
//    @DeleteMapping
//    public void deleteTime(Principal principal){
//        int userId = userDao.findIdByUsername(principal.getName());
//
//        boolean updated = textTimeDao.deleteTime(userId);
//        if (!updated){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time not Updated");
//        }
//    }
//
//
//
//}
