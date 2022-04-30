package edu.colval.javase.ballotbox.forumservice.api;

import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import edu.colval.javase.ballotbox.forumservice.dal.IForumDAO;
import edu.colval.javase.ballotbox.forumservice.dal.IPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
//localhost:8083/api/forumService
@RequestMapping("/api/forumService")
public class ForumRestService {

    @Autowired
    IPostDAO postDAO;

    @Autowired
    IForumDAO forumDAO;
    //Post
    @PostMapping("/makePost")
    public Post createPost(@RequestBody Post newPost){
        this.postDAO.createPost(newPost);
        return newPost;
    }

    @RequestMapping("/findAllPosts")
    public List<Post> searchAllPosts(){
        return this.postDAO.findAllPosts();
    }

    @RequestMapping("/findPost/{id}")
    public Post searchPostById(@PathVariable("id") int id){
        return this.postDAO.findPostById(id);
    }

    @DeleteMapping("/deletePost/{id}")
    public boolean deletePostById(@PathVariable("id") int id){
        boolean result = this.postDAO.deletePostById(id);
        return result;
    }

    //Forum
    @PostMapping("/createForum")
    public Forum createForum(@RequestBody Forum newForum){
        this.forumDAO.createForum(newForum);
        return newForum;
    }

    @RequestMapping("/findAllForums")
    public List<Forum> searchAllForums(){
        return this.forumDAO.findAllForums();
    }

    @RequestMapping("/findForum/{id}")
    public Forum searchForumById(@PathVariable("id") int id){
        return this.forumDAO.findForumById(id);
    }

    @RequestMapping("/findPosts/byForum/{forumId}")
    public List<Post> searchPostsByForumId(@PathVariable("forumId") int forumId){
        return this.postDAO.fetchPostsByForumId(forumId);
    }

    @DeleteMapping("/deleteForum/{id}")
    public boolean deleteForumById(@PathVariable("id") int id){
        boolean result = this.forumDAO.deleteForumById(id);
        return result;
    }
}
