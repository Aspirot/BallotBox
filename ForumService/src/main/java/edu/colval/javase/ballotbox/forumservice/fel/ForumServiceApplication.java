package edu.colval.javase.ballotbox.forumservice.fel;

import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import edu.colval.javase.ballotbox.forumservice.dal.ForumDAO;
import edu.colval.javase.ballotbox.forumservice.dal.PostDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ForumServiceApplication {

    //public static void main(String[] args) {
    //    SpringApplication.run(ForumServiceApplication.class, args);
    //}

    public static void main(String[] args) {
        PostDAO postDAO = new PostDAO();
        List<Post> posts = postDAO.findAllPosts();
        posts.stream().forEach(post -> System.out.println(post.toString()));
        postDAO.createPost(new Post(5, LocalDate.parse("2020-01-20"),"hello im new here",3,5));
        posts = postDAO.findAllPosts();
        posts.stream().forEach(post -> System.out.println(post.toString()));


        ForumDAO forumDAO = new ForumDAO();
        List<Forum> forums = forumDAO.findAllForums();
        forums.stream().forEach(forum -> System.out.println(forum.toString()));
    }
}
