package edu.colval.javase.ballotbox.forumservice.fel;

import edu.colval.javase.ballotbox.forumservice.api.ForumRestService;
import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import edu.colval.javase.ballotbox.forumservice.dal.ForumDAO;
import edu.colval.javase.ballotbox.forumservice.dal.IForumDAO;
import edu.colval.javase.ballotbox.forumservice.dal.IPostDAO;
import edu.colval.javase.ballotbox.forumservice.dal.PostDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackageClasses = ForumRestService.class)
public class ForumServiceApplication {

    @Bean
    public IPostDAO getPostDAO(){
        return new PostDAO();
    }

    @Bean
    public IForumDAO getForumDAO(){
        return new ForumDAO();
    }

    public static void main(String[] args) {
        SpringApplication.run(ForumServiceApplication.class, args);
    }

    /*public static void main(String[] args) {
        PostDAO postDAO = new PostDAO();
        //List<Post> posts = postDAO.findAllPosts();
        //posts.stream().forEach(post -> System.out.println(post.toString()));
        //postDAO.createPost(new Post(5, LocalDate.parse("2020-01-20"),"hello im new here",3,5));
        //posts = postDAO.findAllPosts();
        //posts.stream().forEach(post -> System.out.println(post.toString()));
        System.out.println(postDAO.findPostById(1).toString());

        ForumDAO forumDAO = new ForumDAO();
        //List<Forum> forums = forumDAO.findAllForums();
        //forums.stream().forEach(forum -> System.out.println(forum.toString()));
        //forumDAO.createForum(new Forum(4,"I'm newly created",LocalDate.parse("1111-01-11")));
        //forums = forumDAO.findAllForums();
        //forums.stream().forEach(forum -> System.out.println(forum.toString()));
    }*/
}
