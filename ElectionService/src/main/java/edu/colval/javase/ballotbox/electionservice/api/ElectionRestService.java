package edu.colval.javase.ballotbox.electionservice.api;

import edu.colval.javase.ballotbox.electionservice.bll.control.ElectionController;
import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;
import edu.colval.javase.ballotbox.electionservice.bll.model.Candidate;
import edu.colval.javase.ballotbox.electionservice.dal.*;

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
    private WebClient client;

    I_SQL_Connector sql_connector = new Alwaysdata_SQL_Connector();
    private IBallotDAO ballotDAO = new BallotDAO(sql_connector);
    private ICandidateDAO candidateDAO = new CandidateDAO(sql_connector);
    private ElectionController electionController;
    //Ballot
    @PostMapping("/ballot/create")
    public void createBallot(@RequestBody Ballot newBallot){
        this.ballotDAO.addBallot(newBallot);
    }

    @GetMapping(value = "/ballot/getWinner", produces = "application/json; charset=UTF-8")
    public Integer findWinnerOfElection(@RequestParam Map<String, String> electionWinnerQuery){
        String scanType = electionWinnerQuery.get("type");
        Integer pollId = Integer.valueOf(electionWinnerQuery.get("pollId"));
        electionController = new ElectionController(ballotDAO);
        if (scanType.equals("poly-scan")) {
            String uri = "http://localhost:8084/api/voteService/findVotesByBallot/" + pollId;
            List<Vote_DTO> allBallotVotes = this.fetchVotesWithQuery(uri);
            return this.electionController.polyScan(pollId, allBallotVotes);
        } else if (scanType.equals("lone-scan")) {
            String uri = String.format("http://localhost:8084/api/voteService/findVotesByPollAndRank/%d/%d",pollId,1);
            List<Vote_DTO> allRank1Votes = this.fetchVotesWithQuery(uri);
            return this.electionController.loneScan(pollId,allRank1Votes);
        }
        return -1;
    }

    @GetMapping("/fromElector/addToElection")
    public boolean addElectorToBallot(@RequestParam Map<String, String> candidateParticipationQuery){
        Integer electorId = Integer.parseInt(candidateParticipationQuery.get("electorId"));
        Integer pollId = Integer.parseInt(candidateParticipationQuery.get("pollId"));
        this.ballotDAO.addElectorToBallot(electorId,pollId);
        return true;
    }

    @GetMapping("/ballot/findAll")
    public List<Ballot> fetchAllBallots(){
        return this.ballotDAO.getAllBallots();
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

    public List<Vote_DTO> fetchVotesWithQuery(String uri){
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

    @GetMapping("/candidate/findAll")
    public List<Candidate> findAllCandidates(){
        return this.candidateDAO.getAllCandidates();
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

    @DeleteMapping("/candidate/delete/{candidateId}")
    public Candidate deleteCandidateById(@PathVariable("candidateId") int candidateId) throws SQLException {
        Candidate deletedCandidate = this.candidateDAO.fetchCandidateById(candidateId);
        this.candidateDAO.deleteCandidateById(candidateId);
        return deletedCandidate;
    }

}
