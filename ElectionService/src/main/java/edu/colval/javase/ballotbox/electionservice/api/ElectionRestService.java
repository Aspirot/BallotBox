package edu.colval.javase.ballotbox.electionservice.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.Gson;
import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;
import edu.colval.javase.ballotbox.electionservice.dal.IBallotDAO;
import edu.colval.javase.ballotbox.electionservice.dal.ICandidateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/electionService")
public class ElectionRestService {
    @Autowired
    ICandidateDAO candidateDAO;
    @Autowired
    IBallotDAO ballotDAO;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/createBallot")
    public Ballot createBallot(@RequestBody Ballot newBallot){
        this.ballotDAO.addBallot(newBallot);
        return newBallot;
    }

    @RequestMapping("/getElectionWinner/{pollId}/{type}")
    public Integer findWinnerOfElection(@PathVariable("pollId") int pollId,@PathVariable("type") String scanType){
        if(scanType.equals("poly-scan")){

        }else if (scanType.equals("lone-scan")){

        }
        return null;
    }

    @RequestMapping("/getNumberOfVotesByRank/{candidateId}/{pollId}/{rank}")
    public Integer  findNumberOfInFavorByCandidate(@PathVariable("candidateId") int candidateId,@PathVariable("pollId") int pollId,@PathVariable("rank") int rank){
        String uri = String.format("http://localhost:8084/api/voteService/findVoteByCandidatePollAndRank/%d/%d/%d",candidateId,pollId,rank);
        List<Vote_DTO> vote_dtos = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(uri)).build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200){
                ObjectMapper mapper = new ObjectMapper();
                Gson gson = new Gson();
                mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                List<Vote_DTO> votes = (Arrays.stream(mapper.convertValue(gson.toJson(response.body()), Vote_DTO[].class)).toList());
            }
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

        return vote_dtos.size();
    }

}
