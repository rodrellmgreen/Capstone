package com.techelevator.dao;

import com.techelevator.model.Profile;

import java.util.List;

public interface ProfileDao {

    Profile getProfileById(int id);

    Profile findProfileByUsername(String username);

    boolean updateProfile(Profile profile);

    boolean deleteProfile(String username);

    Profile createProfile(Profile profile);

    int getUserIdByUsername(String username);

    int getProfileIdByUsername(String username);

    String getDisplayNameFromProfileId(int id);

    String getUserPhoneNumber(String userName);

}
