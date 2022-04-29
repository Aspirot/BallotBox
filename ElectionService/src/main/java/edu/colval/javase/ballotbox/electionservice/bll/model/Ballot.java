package edu.colval.javase.ballotbox.electionservice.bll.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ballot {
    private static int AUTO_INCREMENT_ID = 1;

    private int id;
    private String title;
    private LocalDate start;
    private LocalDate end;
    private Boolean isPublic;
    private Boolean isAnonymous;
    private List<Integer> candidatesId;
    private List<Integer> electorsId;
    private int forumId;
    private int ownerId;

    public Ballot(String title, LocalDate start, LocalDate end, Boolean isPublic, Boolean isAnonymous, int forumId, int ownerId) {
        this.id = AUTO_INCREMENT_ID++;
        this.title = title;
        this.start = start;
        this.end = end;
        this.isPublic = isPublic;
        this.isAnonymous = isAnonymous;
        this.candidatesId = new ArrayList<>();
        this.electorsId = new ArrayList<>();
        this.forumId = forumId;
        this.ownerId = ownerId;
    }

    public void addCandidate(Integer candidate){
        this.candidatesId.add(candidate);
    }

    public void addElector(Integer elector){
        this.electorsId.add(elector);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public List<Integer> getCandidates() {
        return candidatesId;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidatesId = candidates;
    }

    public List<Integer> getElectors() {
        return electorsId;
    }

    public void setElectors(List<Integer> electors) {
        this.electorsId = electors;
    }

    public int getForumId() {
        return forumId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Ballot{" +
                "id=" + id +
                ",\n title='" + title + '\'' +
                ",\n start=" + start +
                ", end=" + end +
                ",\n isPublic=" + isPublic +
                ", isAnonymous=" + isAnonymous +
                ",\n candidates=" + candidatesId +
                ",\n electors=" + electorsId +
                ",\n forumId=" + forumId +
                ", ownerId=" + ownerId +
                '}';
    }
}
