package edu.colval.javase.ballotbox.forumservice.dal;

import edu.colval.javase.ballotbox.forumservice.bll.model.Forum;
import org.bson.Document;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ForumCodec {
    public static Document convert(Forum forum) {
        Document document = new Document();
        document.put("id", forum.getId());
        document.put("title", forum.getTitle());
        document.put("createdOn", forum.getCreatedOn());
        return document;
    }

    public static Forum decode(Document document) {
        Forum forum = new Forum();
        forum.setId(document.getInteger("id"));
        forum.setTitle(document.getString("title"));
        forum.setCreatedOn(LocalDate.parse(document.getString("createdOn")));
        return forum;
    }
}
