package edu.colval.javase.ballotbox.forumservice.bll.control;

import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import edu.colval.javase.ballotbox.forumservice.dal.IForumDAO;
import edu.colval.javase.ballotbox.forumservice.dal.IPostDAO;

import java.time.LocalDate;
import java.util.List;


public class ForumController {
    IForumDAO forumDAO;
    IPostDAO postDAO;

    public ForumController(IForumDAO forumDAO, IPostDAO postDAO) {
        this.forumDAO = forumDAO;
        this.postDAO = postDAO;
    }

    //Post
    public void createPost(int id, String date ,String message,int forumId,int electorId){
        Post post = new Post(id, LocalDate.parse(date),message,forumId,electorId);
        this.postDAO.createPost(post);
    }

    public List<Post> searchAllPosts(){
        return this.postDAO.findAllPosts();
    }

    public Post searchPostById(int id){
        Post found = null;
        found = this.postDAO.findPostById(id);
        return found;
    }

    public void deletePostById(int id){
        this.postDAO.deletePostById(id);
    }

    //Forum
    public void createForum(int id, String title,String date){
        Forum forum = new Forum(id,title,LocalDate.parse(date));
        this.forumDAO.createForum(forum);
    }

    public List<Forum> searchAllForums(){
        return this.forumDAO.findAllForums();
    }

    public Forum searchForumById(int id){
        Forum found = null;
        found = this.forumDAO.findForumById(id);
        return found;
    }

    public void deleteForumById(int id){
        this.forumDAO.deleteForumById(id);
    }
}
