package edu.colval.javase.ballotbox.forumservice.fel;

import edu.colval.javase.ballotbox.forumservice.api.ForumRestService;
import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import edu.colval.javase.ballotbox.forumservice.dal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
@ComponentScan(basePackageClasses = ForumRestService.class)
public class ForumServiceApplication {

    I_Mongodb_Connector mongodbConnector = new Atlas_Mongodb_Connector();
    @Bean
    public IPostDAO getPostDAO(){
        return new PostDAO(mongodbConnector);
    }

    @Bean
    public IForumDAO getForumDAO(){
        return new ForumDAO(mongodbConnector);
    }


//dafuck
    public static void main(String[] args) {
        //run_usingOnlineRepository();
        SpringApplication.run(ForumServiceApplication.class,args);
    }

    private static void run_usingOnlineRepository() {
        PostDAO postDAO = new PostDAO(new Atlas_Mongodb_Connector());
        List<Post> posts = postDAO.findAllPosts();
        posts.stream().forEach(post -> System.out.println(post.toString()));
        postDAO.createPost(new Post(5, LocalDate.parse("2020-01-20"),"hello im new here",3,5));
        posts = postDAO.findAllPosts();
        posts.stream().forEach(post -> System.out.println(post.toString()));
        System.out.println(postDAO.findPostById(1).toString());

        ForumDAO forumDAO = new ForumDAO(new Atlas_Mongodb_Connector());
        List<Forum> forums = forumDAO.findAllForums();
        forums.stream().forEach(forum -> System.out.println(forum.toString()));
        forumDAO.createForum(new Forum(4,"I'm newly created",LocalDate.parse("1111-01-11")));
        forums = forumDAO.findAllForums();
        forums.stream().forEach(forum -> System.out.println(forum.toString()));
    }
}