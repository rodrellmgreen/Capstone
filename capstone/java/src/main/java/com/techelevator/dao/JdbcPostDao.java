package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }


    @Override
    public Post getPost(int postId) {
        Post post=new Post();
        String sql="SELECT * FROM community_posts WHERE post_id=?";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,postId);
        if(results.next()){
            post=mapRowToPost(results);
        }
        return post;
    }

    @Override
    public void createPost(Post post) {
      String sql="INSERT INTO community_posts (team_id, user_id, post) VALUES (?,?,?);";
      jdbcTemplate.update(sql,post.getTeamId(),post.getUserId(),post.getMessage());
    }

    @Override
    public boolean updatePost(Post post) {
        boolean success=false;
        String sql="UPDATE community_posts SET post=? WHERE post_id=? AND user_id=?;";
        int linesUpdated=jdbcTemplate.update(sql,post.getMessage(),post.getPostId(), post.getUserId());
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    @Override
    public boolean deletePost(int postId) {
        boolean success=false;
        String sql="DELETE FROM community_posts WHERE post_id=?";
        int linesUpdated= jdbcTemplate.update(sql,postId);
        if(linesUpdated==1){
            success=true;
        }
        return success;
    }

    @Override
    public List<Post> getAllPostsForTeam(int teamId) {
        String sql="SELECT * FROM community_posts WHERE team_id=?";
        List<Post> allTeamPosts=new ArrayList<>();
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,teamId);
        while(results.next()){
            allTeamPosts.add(mapRowToPost(results));
        }
        return allTeamPosts;
    }

    @Override
    public List<Post> getAllPostsForUser(int userId) {
        String sql="SELECT * FROM community_posts WHERE user_id=?";
        List<Post> allUsersPosts=new ArrayList<>();
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,userId);
        while(results.next()){
            allUsersPosts.add(mapRowToPost(results));
        }
        return allUsersPosts;
    }

    @Override
    public int getUserIdFromPostId(int postId) {
        String sql="SELECT user_id FROM community_posts WHERE post_id=?";
        int userId=jdbcTemplate.queryForObject(sql,Integer.class,postId);
        return userId;
    }

    @Override
    public String getDisplayNameFromUserId(int userId) {
        String sql="SELECT display_name FROM profile WHERE user_id=?";
        String name=jdbcTemplate.queryForObject(sql,String.class,userId);
        return name;
    }

    private Post mapRowToPost(SqlRowSet results){
        Post post=new Post();
        post.setPostId(results.getInt("post_id"));
        post.setTeamId(results.getInt("team_id"));
        post.setUserId(results.getInt("user_id"));
        post.setMessage(results.getString("post"));
        int userId=post.getUserId();
        String displayName=getDisplayNameFromUserId(userId);
        post.setDisplayName(displayName);
        return post;
    }
}
