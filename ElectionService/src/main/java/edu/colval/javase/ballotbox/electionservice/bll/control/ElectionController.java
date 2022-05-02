package edu.colval.javase.ballotbox.electionservice.bll.control;

import edu.colval.javase.ballotbox.electionservice.api.Vote_DTO;
import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;
import edu.colval.javase.ballotbox.electionservice.dal.IBallotDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ElectionController {
    private IBallotDAO ballotDAO;

    public ElectionController(IBallotDAO ballotDAO) {
        this.ballotDAO = ballotDAO;
    }

    public void createBallot(Ballot newBallot){
        this.ballotDAO.addBallot(newBallot);
    }

    public void addElectorToBallot(Integer electorId, Integer pollId){
        this.ballotDAO.addCandidateToBallot(electorId,pollId);
    }
    public List<Ballot> fetchAllBallots(){
        return this.ballotDAO.getAllBallots();
    }

    public Ballot findBallotById(int pollId){
        return this.ballotDAO.fetchBallotById(pollId);
    }

    public Integer findNumberOfElectorsForBallot(int pollId){
        return this.ballotDAO.fetchBallotById(pollId).getElectors().size();
    }

    public Ballot deleteBallotById(int pollId) throws SQLException {
        Ballot deletedBallot = this.ballotDAO.fetchBallotById(pollId);
        this.ballotDAO.deleteBallotById(pollId);
        return deletedBallot;
    }

    public Integer polyScan(int pollId, List<Vote_DTO> votes){

        int numberOfOptions = 0;
        int winnerPoint= 0;
        int winner=-1;
        numberOfOptions = this.ballotDAO.fetchBallotById(pollId).getCandidates().size();
        int currentCandidatePoints;
        for (Integer c : this.ballotDAO.fetchBallotById(pollId).getCandidates())
        {
            currentCandidatePoints = 0;
            for(Vote_DTO vote: votes)
            {
                if(c==vote.getPollSubjectId())
                {
                    for(int p=1,r=numberOfOptions; r>0;r--,p++)
                    {
                        if(vote.getRank()==p)
                        {
                            currentCandidatePoints=currentCandidatePoints+r;
                        }
                    }
                }
            }
            //System.out.println("cand " + c + " has " + currentCandidatePoints);
            if(currentCandidatePoints>winnerPoint)
            {
                winnerPoint=currentCandidatePoints;
                winner=c;
            }
        }
        return winner;
    }

    public Integer loneScan(int pollId,List<Vote_DTO> rankOneVotes){
        int winner = -1;
        int winnerNumb = -1;
        for (Integer candidate:this.ballotDAO.fetchBallotById(pollId).getCandidates()) {
            int currentCand = rankOneVotes.stream().filter(v -> v.getPollSubjectId()==candidate).collect(Collectors.toList()).size();
            if(winnerNumb<currentCand){
                winner=candidate;
                winnerNumb = currentCand;
            }
        }
        return winner;
    }
}
