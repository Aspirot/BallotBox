package edu.colval.javase.ballotbox.forumservice.dal;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ForumDAO {

    I_Mongodb_Connector mongodbConnector = null;
    List<Forum> forums = null;
    List<Post> posts = null;

    public ForumDAO() {
        this.mongodbConnector = new Atlas_Mongodb_Connector();
        findAllForums();
        findAllForums();
    }

    public List<Forum> findAllForums() {
        this.forums = new ArrayList<>();
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Forums");
        FindIterable<Document> documents = collection.find();
        for(var doc : documents)
            this.forums.add(ForumCodec.decode(doc));
        return this.forums;
    }

    public Forum createForum(){
        return null;
    }
}
