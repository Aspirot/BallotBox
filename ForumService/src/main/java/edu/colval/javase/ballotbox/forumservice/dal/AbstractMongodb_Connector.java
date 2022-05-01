package edu.colval.javase.ballotbox.forumservice.dal;

import com.mongodb.client.MongoDatabase;

public class AbstractMongodb_Connector {
    protected String host;
    protected String user;
    protected String password;
    protected String dbname;

    protected MongoDatabase database;
}
