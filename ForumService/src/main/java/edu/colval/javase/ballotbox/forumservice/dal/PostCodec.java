package edu.colval.javase.ballotbox.forumservice.dal;

import edu.colval.javase.ballotbox.forumservice.bll.model.Post;
import org.bson.Document;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PostCodec {
    public static Document convert(Post post) {
        Document document = new Document();
        document.put("id", post.getId());
        document.put("date", post.getDate());
        document.put("message", post.getMessage());
        document.put("forumId", post.getForumId());
        document.put("electorId", post.getElectorId());
        return document;
    }
    private int id;
    private LocalDate date;
    private String message;
    private int forumId;
    private int electorId;


    public static Post decode(Document document) {
        Post post = new Post();
        post.setId(document.getInteger("id"));
        post.setDate(LocalDate.parse(document.getString("date")));
        post.setMessage(document.getString("message"));
        post.setForumId(document.getInteger("forumId"));
        post.setElectorId(document.getInteger("electorId"));

        return post;
    }
}
