package edu.colval.javase.ballotbox.forumservice.dal;

import com.google.gson.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import org.bson.Document;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    I_Mongodb_Connector mongodbConnector = null;
    List<Post> posts = null;

    public PostDAO() {
        this.mongodbConnector = new Atlas_Mongodb_Connector();
        findAllPosts();
    }


    public List<Post> findAllPosts() {
        this.posts = new ArrayList<>();
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Posts");
        FindIterable<Document> documents = collection.find();
        for(var doc : documents)
            this.posts.add(PostCodec.decode(doc));
        return this.posts;
    }



    public void createPost (Post newPost) {
        MongoCollection collection = this.mongodbConnector.getDatabase()
                .getCollection("Posts");
        Document bson = PostCodec.convert(newPost);
        collection.insertOne(bson);
    }



}
