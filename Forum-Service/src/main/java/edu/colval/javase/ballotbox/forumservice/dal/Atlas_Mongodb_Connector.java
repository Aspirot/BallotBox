package edu.colval.javase.ballotbox.forumservice.dal;


import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Atlas_Mongodb_Connector extends AbstractMongodb_Connector implements I_Mongodb_Connector {

    public Atlas_Mongodb_Connector(){
        this.host = "boeycluster";
        this.user = "boey";
        this.password = "forAmine";
        this.dbname = "JavaSE_W22";

        connect();
    }

    public void connect() {

        String connectionString = String.format("mongodb+srv://%s:%s@%s.fuzbh.mongodb.net/%s?retryWrites=true&w=majority",this.user,this.password,this.host,this.dbname);
        MongoClientURI uri = new MongoClientURI(connectionString);
        MongoClient mongoClient = new MongoClient(uri);

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        this.database = mongoClient.getDatabase(dbname).withCodecRegistry(pojoCodecRegistry);

        System.out.println(this.database.getName() + " is connected!");
    }


    public MongoDatabase getDatabase() {
        return this.database;
    }
}
