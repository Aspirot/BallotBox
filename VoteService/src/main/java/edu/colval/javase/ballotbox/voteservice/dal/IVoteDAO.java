package edu.colval.javase.ballotbox.voteservice.dal;

import edu.colval.javase.ballotbox.voteservice.bll.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface IVoteDAO {
    public void createVote(LocalDate when, int rank, int pollId, int pollSubjectId, int electorId);
    public List<Vote> findAllVotes();
    public List<Vote> getAllVotesForBallot(int searchedPollId);
    public Vote fetchVoteById(int searchedVoteId);
    public Vote fetchVoteByCandidateId_PollId_Rank(int searchedCandidateId, int searchedPollId, int searchRank);
    public Vote fetchVoteByElectorId_PollId_Rank(int searchedElectorId, int searchedPollId, int searchedRank);
}
