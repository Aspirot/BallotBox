package edu.colval.javase.ballotbox.forumservice.api;

import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import edu.colval.javase.ballotbox.forumservice.dal.IForumDAO;
import edu.colval.javase.ballotbox.forumservice.dal.IPostDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/forumService")
public class ForumRestService {

    @Autowired
    IForumDAO forumDAO;
    @Autowired
    IPostDAO postDAO;

    //Post
    @RequestMapping("/makePost/{id}/{date}/{message}/{forumId}/{electorId}")
    public boolean createPost(@PathVariable("id") int id, @PathVariable("date") String date , @PathVariable("message") String message, @PathVariable("forumId") int forumId, @PathVariable("electorId") int electorId){
        Post post = new Post(id, LocalDate.parse(date),message,forumId,electorId);
        this.postDAO.createPost(post);
        return true;
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

    @RequestMapping("/deletePost/{id}")
    public boolean deletePostById(@PathVariable("id") int id){
        this.postDAO.deletePostById(id);
        return true;
    }

    //Forum
    @RequestMapping("/createForum/{id}/{title}/{date}")
    public void createForum(@PathVariable("id") int id,@PathVariable("title") String title,@PathVariable("date") String date){
        Forum forum = new Forum(id,title,LocalDate.parse(date));
        this.forumDAO.createForum(forum);
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

    @RequestMapping("/deleteForum/{id}")
    public void deleteForumById(@PathVariable("id") int id){
        this.forumDAO.deleteForumById(id);
    }
}
