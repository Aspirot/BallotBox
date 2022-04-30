package edu.colval.javase.ballotbox.electionservice.api;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({ "rank", "pollId", "pollSubjectId", "electorId" })
public class Vote_DTO {

    private int rank;
    private int pollId;
    private int pollSubjectId;
    private int electorId;

    public Vote_DTO(int rank, int pollId, int pollSubjectId, int electorId) {
        this.rank = rank;
        this.pollId = pollId;
        this.pollSubjectId = pollSubjectId;
        this.electorId = electorId;
    }

    public Vote_DTO() {
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

    public int getPollSubjectId() {
        return pollSubjectId;
    }

    public void setPollSubjectId(int pollSubjectId) {
        this.pollSubjectId = pollSubjectId;
    }

    public int getElectorId() {
        return electorId;
    }

    public void setElectorId(int electorId) {
        this.electorId = electorId;
    }
}
