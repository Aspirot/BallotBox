package edu.colval.javase.ballotbox.forumservice.dal;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import org.bson.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ForumDAO implements IForumDAO{

    I_Mongodb_Connector mongodbConnector = null;

    public ForumDAO(I_Mongodb_Connector connector) {
        this.mongodbConnector = connector;
    }

    public List<Forum> findAllForums() {
        List<Forum> forums = new ArrayList<>();
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Forums");
        FindIterable<Document> documents = collection.find();
        for(var doc : documents)
            forums.add(ForumCodec.decode(doc));
        return forums;
    }

    public Forum findForumById(int id) {
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Forums");
        Forum found = ForumCodec.decode(collection.find(eq("id",id)).first());
        return found;
    }

    public void createForum(Forum newForum){
        MongoCollection collection = this.mongodbConnector.getDatabase()
                .getCollection("Forums");
        Document bson = ForumCodec.convert(newForum);
        collection.insertOne(bson);
    }

    public boolean deleteForumById(int id){
        MongoCollection<Document> collection =
                this.mongodbConnector.getDatabase().getCollection("Forums");
        var result = collection.deleteOne(eq("id",id));
        return result.wasAcknowledged();
    }
}
