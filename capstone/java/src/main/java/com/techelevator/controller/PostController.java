package com.techelevator.controller;


import com.techelevator.dao.PostDao;
import com.techelevator.dao.ProfileDao;
import com.techelevator.dao.TeamDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RequestMapping(path="/posts")
@CrossOrigin
@RestController
public class PostController {

    @Autowired
    PostDao postDao;

    @Autowired
    TeamDao teamDao;

    @Autowired
    ProfileDao profileDao;

    @GetMapping(path="/{postId}")
    public Post getSpecificPost(@PathVariable int postId){
        return postDao.getPost(postId);
    }
    @GetMapping(path="/allTeam/{teamId}")
    public List<Post> getAllPostsForTeam(@PathVariable int teamId){
        return postDao.getAllPostsForTeam(teamId);
    }
    @GetMapping(path="/allUser")
    public List<Post> getAllPostsForUser(Principal principal){
        int userId= profileDao.getUserIdByUsername(principal.getName());
        return postDao.getAllPostsForUser(userId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public void createAPost(Principal principal,@RequestBody Post post){
        int userId= profileDao.getUserIdByUsername(principal.getName());
        String displayName= postDao.getDisplayNameFromUserId(userId);
        post.setUserId(userId);
        post.setDisplayName(displayName);
       postDao.createPost(post);
    }

    @PutMapping()
    public void updatePost(Principal principal,@RequestBody Post post){
        int userId= profileDao.getUserIdByUsername(principal.getName());
        String displayName= postDao.getDisplayNameFromUserId(userId);
        post.setUserId(userId);
        post.setDisplayName(displayName);
        boolean updated=postDao.updatePost(post);
        if(!updated){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Post not updated");
        }
    }

    @DeleteMapping(path="/{postId}")
    public void deleteAPost(Principal principal,@PathVariable int postId){
        Post post=postDao.getPost(postId);
        int userId= profileDao.getUserIdByUsername(principal.getName());
       if(userId!=post.getUserId()){
          throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You can not delete other users posts");
        }else {
            boolean updated = postDao.deletePost(postId);
            if (!updated) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not deleted");
            }
        }
    }


}
