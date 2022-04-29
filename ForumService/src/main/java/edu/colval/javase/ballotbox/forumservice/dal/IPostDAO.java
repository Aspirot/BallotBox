package edu.colval.javase.ballotbox.forumservice.dal;


import edu.colval.javase.ballotbox.forumservice.bll.model.Post;

import java.util.List;

public interface IPostDAO {
    void createPost(Post post);
    List<Post> findAllPosts();
    Post findPostById(int id);
    void deletePostById(int id);
}
