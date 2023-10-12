//package com.techelevator.dao;
//
//import com.techelevator.model.TextTime;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//import org.springframework.stereotype.Component;
//
//import java.sql.Time;
//
//@Component
//public class JdbcTextTime implements TextTimeDao{
//
//    private JdbcTemplate jdbcTemplate;
//    public JdbcTextTime(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public TextTime getTime(int userId) {
//        String sql = "SELECT * FROM text_time WHERE user_id = ?";
//        TextTime textTime = new TextTime();
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//        if (results.next()){
//            textTime = mapRowToTextTime(results);
//        }
//        return textTime;
//    }
//
//    @Override
//    public void createTime(TextTime textTime) {
//        String sql = "INSERT INTO text_time (user_id, time_stamp) VALUES (?,?) RETURNING text_id";
//        Integer textId = jdbcTemplate.queryForObject(sql, Integer.class, textTime.getUserId(), textTime.getTextTime());
//
//    }
//
//    @Override
//    public boolean updateTime(TextTime textTime) {
//        String sql = "UPDATE text_time SET time_stamp = ? WHERE user_id = ?";
//        int linesUpdates = jdbcTemplate.update(sql, textTime.getTextTime(),textTime.getUserId());
//        boolean success = false;
//        if (linesUpdates == 1){
//            success = true;
//        }
//        return success;
//    }
//
//    @Override
//    public boolean deleteTime(int userId) {
//        String sql = "DELETE FROM text_time WHERE user_id = ?";
//        int linesUpdates = jdbcTemplate.update(sql, userId);
//        boolean success = false;
//        if (linesUpdates == 1){
//            success = true;
//        }
//        return success;
//
//    }
//
//    private TextTime mapRowToTextTime(SqlRowSet results){
//        TextTime textTime = new TextTime();
//        textTime.setTextId(results.getInt("text_id"));
//        textTime.setUserId(results.getInt("user_id"));
//        textTime.setTextTime(results.getTime("time_stamp"));
//        return textTime;
//    }
//
//}
