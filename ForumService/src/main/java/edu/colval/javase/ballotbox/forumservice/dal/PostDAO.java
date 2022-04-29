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

import static com.mongodb.client.model.Filters.eq;

public class PostDAO implements IPostDAO{

    I_Mongodb_Connector mongodbConnector = null;

    public PostDAO() {
        this.mongodbConnector = new Atlas_Mongodb_Connector();
    }


    public List<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Posts");
        FindIterable<Document> documents = collection.find();
        for(var doc : documents)
            posts.add(PostCodec.decode(doc));
        return posts;
    }

    public List<Post> fetchPostsByForumId(int id) {
        List<Post> posts = new ArrayList<>();
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Posts");
        Document query = new Document("forumId", id);
        FindIterable<Document> documents = collection.find(query);
        for(var doc : documents)
            posts.add(PostCodec.decode(doc));
        return posts;
    }

    public Post findPostById(int id) {
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Posts");
        Post found = PostCodec.decode(collection.find(eq("id",id)).first());
        return found;
    }

    public void createPost (Post newPost) {
        MongoCollection collection = this.mongodbConnector.getDatabase()
                .getCollection("Posts");
        Document bson = PostCodec.convert(newPost);
        collection.insertOne(bson);
    }

    public boolean deletePostById(int id){
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Posts");
        var result = collection.deleteOne(eq("id",id));
        return result.wasAcknowledged();
    }

}
