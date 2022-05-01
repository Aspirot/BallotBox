package edu.colval.javase.ballotbox.electorservice.api;

import edu.colval.javase.ballotbox.electorservice.bll.model.Elector;
import edu.colval.javase.ballotbox.electorservice.dal.IElectorDAO;
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
@RequestMapping("/api/electorService")
public class ElectorRestService {
    @Autowired
    private IElectorDAO electorDAO;
    @Autowired
    private WebClient client;

    @PostMapping("/elector/create")
    public Elector createElector(@RequestBody Elector newElector){
        this.electorDAO.addElector(newElector);
        return this.electorDAO.fetchElectorById(newElector.getId());
    }

    @GetMapping("/elector/findAllElectors")
    public List<Elector> findAllElectors(){
        return this.electorDAO.getAllElectors();
    }

    @GetMapping("/elector/findElector/{electorId}")
    public Elector findElectorById(@PathVariable("electorId") int electorId) throws SQLException{
        Elector foundElector = this.electorDAO.fetchElectorById(electorId);
        return foundElector;
    }

    @GetMapping("/fromElector/addToElection")
    public Boolean addElectorToBallot(@RequestParam Map<String, String> query){
        Integer electorId = Integer.parseInt(query.get("electorId"));
        Integer pollId = Integer.parseInt(query.get("pollId"));
        String uri = String.format("http://localhost:8081/api/electionService/fromElector/addToElection?electorId=%d&pollId=%d",electorId,pollId);

        Boolean result = this.client.get()
                    .uri(uri).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Boolean.class).block();
        return result;
    }

    @DeleteMapping("/elector/delete/{electorId}")
    public Elector deleteBallotById(@PathVariable("electorId") int electorId) throws SQLException {
        Elector deletedElector = this.electorDAO.fetchElectorById(electorId);
        this.electorDAO.deleteElector(electorId);
        return deletedElector;
    }
}
