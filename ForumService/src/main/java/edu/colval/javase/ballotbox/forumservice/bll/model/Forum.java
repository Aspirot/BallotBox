package edu.colval.javase.ballotbox.forumservice.bll.model;

import java.time.LocalDate;

public class Forum {
    private int id;
    private String title;
    private LocalDate createdOn;

    public Forum() {
    }

    public Forum(int  id, String title, LocalDate createdOn) {
        this.id = id;
        this.title = title;
        this.createdOn = createdOn;
    }

    public int  getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdOn=" + createdOn +
                '}';

    }

}
