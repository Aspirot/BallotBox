package edu.colval.javase.ballotbox.forumservice.dal;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ForumDAO {

    I_Mongodb_Connector mongodbConnector = null;
    List<Forum> forums = null;

    public ForumDAO() {
        this.mongodbConnector = new Atlas_Mongodb_Connector();
        this.forums = new ArrayList<>();
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
}
