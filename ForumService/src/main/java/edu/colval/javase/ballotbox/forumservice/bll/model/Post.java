package edu.colval.javase.ballotbox.forumservice.bll.model;

import java.time.LocalDate;

public class Post {
    private static int AUTO_INCREMENT_ID = 1;

    private int id;
    private LocalDate date;
    private String message;
    private int forumId;
    private int electorId;

    public Post() {
    }

    public Post(LocalDate date, String message, int forumId, int electorId) {
        this.id = AUTO_INCREMENT_ID++;
        this.date = date;
        this.message = message;
        this.forumId = forumId;
        this.electorId = electorId;
    }

    public Post(int id, LocalDate date, String message, int forumId, int electorId) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.forumId = forumId;
        this.electorId = electorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getForumId() {
        return forumId;
    }

    public int getElectorId() {
        return electorId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public void setElectorId(int electorId) {
        this.electorId = electorId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", date=" + date +
                ", message='" + message + '\'' +
                ", forumId=" + forumId +
                ", electorId=" + electorId +
                '}';
    }
}
