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
        Post found = null;
        found = this.postDAO.findPostById(id);
        return found;
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
        Forum found = null;
        found = this.forumDAO.findForumById(id);
        return found;
    }

    @RequestMapping("/findPosts/byForum/{forumId}")
    public List<Post> searchPostsByForumId(@PathVariable("forumId") int forumId){
        List<Post> posts = postDAO.fetchPostsByForumId(forumId);
        return posts;
    }

    @DeleteMapping("/deleteForum/{id}")
    public boolean deleteForumById(@PathVariable("id") int id){
        boolean result = this.forumDAO.deleteForumById(id);
        return result;
    }
}
