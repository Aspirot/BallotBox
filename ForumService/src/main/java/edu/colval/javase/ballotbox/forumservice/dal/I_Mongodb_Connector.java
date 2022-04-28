package edu.colval.javase.ballotbox.forumservice.dal;

import com.mongodb.client.MongoDatabase;

public interface I_Mongodb_Connector {

    public void connect();
    public MongoDatabase getDatabase();
}
