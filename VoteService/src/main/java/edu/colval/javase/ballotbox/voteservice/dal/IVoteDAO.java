package edu.colval.javase.ballotbox.voteservice.dal;

import edu.colval.javase.ballotbox.voteservice.bll.model.Vote;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IVoteDAO {
    void createVote(LocalDate when, int rank, int pollId, int pollSubjectId, int electorId);
    List<Vote> findAllVotes();
    List<Vote> getAllVotesForBallot(int searchedPollId);
    Vote fetchVoteById(int searchedVoteId);
    Vote fetchVoteByCandidateId_PollId_Rank(int searchedCandidateId, int searchedPollId, int searchRank);
    Vote fetchVoteByElectorId_PollId_Rank(int searchedElectorId, int searchedPollId, int searchedRank);
    void deleteVoteById(int id) throws SQLException;
}
