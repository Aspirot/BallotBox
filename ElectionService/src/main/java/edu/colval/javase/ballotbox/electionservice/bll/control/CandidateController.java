package edu.colval.javase.ballotbox.electionservice.bll.control;

import edu.colval.javase.ballotbox.electionservice.dal.ICandidateDAO;

public class CandidateController {
    private static ICandidateDAO candidateDAO;

    public CandidateController(ICandidateDAO candidateDAO){
        this.candidateDAO = candidateDAO;
    }
/*
    //added, not used
    public void addCandidateToElection(int candidateId, int pollId)
    {
        BallotController.getBallotDAO().fetchBallotById(pollId).get().addCandidate(candidateDAO.getAllCanditates().stream().filter(c -> c.getId()==candidateId).findFirst().get());
    }

    public static int findNumberOfInFavorByCandidate(int candidateId,int pollId,int rank){
        return VoteController.getVoteDAO().getAllVotes().stream().filter(v -> v.getPollSubjectId()==candidateId&&v.getRank()==rank&&v.getPollId()==pollId).toList().size();
    }

    public static ICandidateDAO getCandidateDAO() {
        return candidateDAO;
    }*/
}
