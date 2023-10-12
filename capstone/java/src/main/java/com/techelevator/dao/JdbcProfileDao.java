package com.techelevator.dao;

import com.techelevator.model.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
@Component
public class JdbcProfileDao implements ProfileDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int getProfileIdByUsername(String username){
        int profileId=0;
        String sql="SELECT p.profile_id FROM profile p " +
                "JOIN users u ON p.user_id=u.user_id "+
                "WHERE u.username=?";
        try{
            profileId=jdbcTemplate.queryForObject(sql,Integer.class,username);
        }catch(DataAccessException e){
            System.out.println("Error");
        }
        return profileId;
    }

    @Override
    public String getDisplayNameFromProfileId(int id) {
        String sql="SELECT display_name FROM profile WHERE profile_id=?;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,id);
        String displayName="";
        if(results.next()){
            displayName= results.getString("display_name");
        }
        return displayName;
    }

    @Override
    public int getUserIdByUsername(String username){
        int userId=0;
        String sql="SELECT user_id FROM users u " +
                "WHERE username=?";
        try{
            userId=jdbcTemplate.queryForObject(sql,Integer.class,username);
        }catch(DataAccessException e){
            System.out.println("Error");
        }
        return userId;
    }

    @Override
    public Profile getProfileById(int id) {
        String sql="SELECT * FROM profile WHERE profile_id=?;";
        SqlRowSet results=jdbcTemplate.queryForRowSet(sql,id);
        Profile profile=new Profile();
        if(results.next()){
            profile=mapRowToProfile(results);
        }
        return profile;
    }

    @Override
    public Profile findProfileByUsername(String username) {
        String sql="SELECT * FROM profile p " +
                "JOIN users u ON p.user_id=u.user_id "+
                "WHERE username=?";
        SqlRowSet results=jdbcTemplate.queryForRowSet(sql,username);
        Profile profile=new Profile();
        if(results.next()){
            profile=mapRowToProfile(results);
        }
        return profile;
    }

    @Override
    public boolean updateProfile(Profile profile) {
        boolean success = false;
        String sql = "UPDATE profile SET email=?, age=?, height_feet=?, height_inches=?, current_weight=?, desired_weight=?, profile_picture=?, display_name=?, public_private=?, phone_number=?" +
                "  WHERE profile_id=?;";
        int linesUpdated = jdbcTemplate.update(sql, profile.getEmail(), profile.getAge(),
                profile.getFeet(),profile.getInches(),profile.getCurrentWeight(),profile.getDesiredWeight(),profile.getProfilePicture(),profile.getDisplayName(),profile.isPublicPrivate(),profile.getPhoneNumber(),profile.getProfileId());
        if (linesUpdated == 1) {
            success = true;
        }
        return success;
    }

    @Override
    public boolean deleteProfile(String username) {
        boolean success=false;
        String sql="DELETE FROM profile p " +
                "WHERE profile_id=?";
        int id=getProfileIdByUsername(username);
        int linesUpdated=jdbcTemplate.update(sql,id);
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    @Override
    public Profile createProfile(Profile profile) {
        String sql="INSERT INTO profile (user_id, email, age, height_feet, height_inches, " +
                "current_weight, desired_weight, profile_picture, display_name, public_private, phone_number) VALUES (?,?,?,?,?,?,?,?,?,?,?) RETURNING profile_id";
        Integer profileId=jdbcTemplate.queryForObject(sql,Integer.class,profile.getUserId(),profile.getEmail(),profile.getAge(),
        profile.getFeet(),profile.getInches(),profile.getCurrentWeight(),profile.getDesiredWeight(),profile.getProfilePicture(), profile.getDisplayName(),profile.isPublicPrivate(), profile.getPhoneNumber());

        return getProfileById(profileId);
    }

    @Override
    public String getUserPhoneNumber(String userName){
        String userNumber = "";

        int id=getProfileIdByUsername(userName);

        String sql = "SELECT phone_number FROM profile WHERE profile_id = ?";
        try{
            userNumber = jdbcTemplate.queryForObject(sql, String.class, id);
        }catch (DataAccessException e){
            System.out.println("error");
        }

        return userNumber;
    }


    private Profile mapRowToProfile(SqlRowSet results){
        Profile profile=new Profile();
        profile.setProfileId(results.getInt("profile_id"));
        profile.setUserId(results.getInt("user_id"));
        profile.setEmail(results.getString("email"));
        profile.setAge(results.getInt("age"));
        profile.setFeet(results.getInt("height_feet"));
        profile.setInches((results.getInt("height_inches")));
        profile.setCurrentWeight(results.getDouble("current_weight"));
        profile.setDesiredWeight(results.getDouble("desired_weight"));
        profile.setProfilePicture(results.getString("profile_picture"));
        profile.setDisplayName(results.getString("display_name"));
        profile.setPublicPrivate(results.getBoolean("public_private"));
        profile.setPhoneNumber(results.getString("phone_number"));
        return profile;
    }
}
