package edu.colval.javase.ballotbox.voteservice.bll.model;

import java.time.LocalDate;

public class Vote implements Comparable<Vote>{
    private static int AUTO_INCREMENT_ID = 1;

    private int id;
    private LocalDate when;
    private int rank;
    private int pollId;
    private int pollSubjectId;
    private int electorId;

    public Vote(LocalDate when, int rank, int pollId, int pollSubjectId, int electorId) {
        this.id = AUTO_INCREMENT_ID++;
        this.when = when;
        this.rank = rank;
        this.pollId = pollId;
        this.pollSubjectId = pollSubjectId;
        this.electorId = electorId;
    }

    public Vote(int id, LocalDate when, int rank, int pollId, int pollSubjectId, int electorId) {
        this.id = id;
        this.when = when;
        this.rank = rank;
        this.pollId = pollId;
        this.pollSubjectId = pollSubjectId;
        this.electorId = electorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getWhen() {
        return when;
    }

    public void setWhen(LocalDate when) {
        this.when = when;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public void setElectorId(int electorId) {
        this.electorId = electorId;
    }

    public int getPollSubjectId() {
        return pollSubjectId;
    }

    public void setPollSubjectId(int pollSubjectId) {
        this.pollSubjectId = pollSubjectId;
    }

    public int getElectorId() {
        return electorId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", when=" + when +
                ", rank=" + rank +
                ", pollId=" + pollId +
                ", pollSubjectId=" + pollSubjectId +
                ", electorId=" + electorId +
                '}';
    }

    @Override
    public int compareTo(Vote o) {
        return Integer.compare(this.getRank(),o.getRank());
    }
}
