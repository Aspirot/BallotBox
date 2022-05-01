package edu.colval.javase.ballotbox.electionservice.api;

import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;
import edu.colval.javase.ballotbox.electionservice.bll.model.Candidate;
import edu.colval.javase.ballotbox.electionservice.dal.IBallotDAO;
import edu.colval.javase.ballotbox.electionservice.dal.ICandidateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/electionService")
public class ElectionRestService {
    @Autowired
    private ICandidateDAO candidateDAO;
    @Autowired
    private IBallotDAO ballotDAO;
    @Autowired
    private WebClient client;

    //Ballot
    @PostMapping("/ballot/create")
    public Ballot createBallot(@RequestBody Ballot newBallot){
        this.ballotDAO.addBallot(newBallot);
        return this.ballotDAO.fetchBallotById(newBallot.getId());
    }

    @GetMapping(value = "/ballot/getWinner", produces = "application/json; charset=UTF-8")
    public Integer findWinnerOfElection(@RequestParam Map<String, String> electionWinnerQuery){
        String scanType = electionWinnerQuery.get("type");
        Integer pollId = Integer.valueOf(electionWinnerQuery.get("pollId"));
        int winner = -1;
        if(pollId>0 && pollId<=this.ballotDAO.getAllBallots().size()) {
            if (scanType.equals("poly-scan")) {
                winner = this.polyScan(pollId);
            } else if (scanType.equals("lone-scan")) {
                winner = this.loneScan(pollId);
            }
        }
        return winner;
    }

    @GetMapping("/ballot/find/{pollId}")
    public Ballot findBallotById(@PathVariable("pollId") int pollId){
        return this.ballotDAO.fetchBallotById(pollId);
    }

    @GetMapping("/ballot/getNumberOfElectors/{pollId}")
    public Integer findNumberOfElectorsForBallot(@PathVariable("pollId") int pollId){
        return this.ballotDAO.fetchBallotById(pollId).getElectors().size();
    }

    @DeleteMapping("/ballot/delete/{pollId}")
    public Ballot deleteBallotById(@PathVariable("pollId") int pollId) throws SQLException {
        Ballot deletedBallot = this.ballotDAO.fetchBallotById(pollId);
        this.ballotDAO.deleteBallotById(pollId);
        return deletedBallot;
    }

    private Integer polyScan(int pollId){
        String uri = "http://localhost:8084/api/voteService/findVotesByBallot/" + pollId;
        List<Vote_DTO> allBallotVotes = this.fetchVotesWithQuery(uri);

        int numberOfOptions= 0;
        int winnerPoint= 0;
        int winner=-1;
        numberOfOptions = this.ballotDAO.fetchBallotById(pollId).getCandidates().size();
        int currentCandidatePoints;
        for (Integer c : this.ballotDAO.fetchBallotById(pollId).getCandidates())
        {
            currentCandidatePoints = 0;
            for(Vote_DTO vote: allBallotVotes)
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
            System.out.println("cand " + c + " has " + currentCandidatePoints);
            if(currentCandidatePoints>winnerPoint)
            {
                winnerPoint=currentCandidatePoints;
                winner=c;
            }
        }
        return winner;
    }

    private Integer loneScan(int pollId){
        String uri = String.format("http://localhost:8084/api/voteService/findVotesByPollAndRank/%d/%d",pollId,1);
        List<Vote_DTO> allRank1Votes = this.fetchVotesWithQuery(uri);
        int winner = -1;
        int winnerNumb = -1;
        for (Integer candidate:this.ballotDAO.fetchBallotById(pollId).getCandidates()) {
            int currentCand = allRank1Votes.stream().filter(v -> v.getPollSubjectId()==candidate).toList().size();
            if(winnerNumb<currentCand){
                winner=candidate;
                winnerNumb = currentCand;
            }
        }
        return winner;
    }

    private List<Vote_DTO> fetchVotesWithQuery(String uri){
        List<Vote_DTO> vote_dtos = new ArrayList<>();
        vote_dtos = this.client.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Vote_DTO.class)
                .collectList()
                .block();
        return vote_dtos;
    }

    //Candidates
    @PostMapping("/candidate/createCandidate")
    public Candidate createCandidate(@RequestBody Candidate newCandidate){
        this.candidateDAO.saveCandidate(newCandidate);
        return this.candidateDAO.fetchCandidateById(newCandidate.getId());
    }

    @RequestMapping("/candidate/addCandidateToBallot")
    public void addCandidateToBallot(@RequestParam Map<Integer, Integer> candidateParticipationQuery){
        Integer candidateId = candidateParticipationQuery.get("candidateId");
        Integer pollId = candidateParticipationQuery.get("pollId");
        this.ballotDAO.addCandidateToBallot(candidateId,pollId);
    }

    @GetMapping("/candidate/find/{candidateId}")
    public Candidate findCandidateById(@PathVariable("candidateId") int candidateId){
        return this.candidateDAO.fetchCandidateById(candidateId);
    }

    @GetMapping("/candidate/getNumberOfVotesByRank/{candidateId}/{pollId}/{rank}")
    public Integer  findNumberOfInFavorByCandidate(@PathVariable("candidateId") int candidateId,@PathVariable("pollId") int pollId,@PathVariable("rank") int rank){
        String uri = String.format("http://localhost:8084/api/voteService/findVoteByCandidatePollAndRank/%d/%d/%d",candidateId,pollId,rank);
        return this.fetchVotesWithQuery(uri).size();
    }

}
