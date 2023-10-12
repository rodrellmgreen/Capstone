package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

    Post getPost(int postId);

    void createPost(Post post);

    boolean updatePost(Post post);

    boolean deletePost(int postId);

    List<Post> getAllPostsForTeam(int teamId);

    List<Post> getAllPostsForUser(int userId);

    int getUserIdFromPostId(int postId);

    String getDisplayNameFromUserId(int userId);


}
