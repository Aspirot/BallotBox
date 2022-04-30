package edu.colval.javase.ballotbox.voteservice.dal;

import edu.colval.javase.ballotbox.voteservice.bll.model.Vote;

import java.sql.SQLException;
import java.util.List;

public interface IVoteDAO {
    void createVote(Vote vote);
    List<Vote> findAllVotes();
    List<Vote> getAllVotesForBallot(int searchedPollId);
    Vote fetchVoteById(int searchedVoteId);
    List<Vote> fetchVotesByCandidateId_PollId_Rank(int searchedCandidateId, int searchedPollId, int searchRank);
    Vote fetchVoteByElectorId_PollId_Rank(int searchedElectorId, int searchedPollId, int searchedRank);
    List<Vote> fetchVotesByPollIdAndRank(int pollId, int rank);
    void deleteVoteById(int id) throws SQLException;
}
