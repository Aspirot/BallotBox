package edu.colval.javase.ballotbox.electionservice.fel;

import edu.colval.javase.ballotbox.electionservice.api.ElectionRestService;
import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;
import edu.colval.javase.ballotbox.electionservice.bll.model.Candidate;
import edu.colval.javase.ballotbox.electionservice.dal.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackageClasses = ElectionRestService.class)
public class ElectionServiceApplication {


    @Bean WebClient createWebClient(){return WebClient.create();}

    public static void main(String[] args) {
        SpringApplication.run(ElectionServiceApplication.class, args);
        //testing method
         //run_usingOnlineRepository();
    }

    private static void run_usingOnlineRepository() {
        I_SQL_Connector sql_connector = new Alwaysdata_SQL_Connector();
        IBallotDAO ballotDAO = new BallotDAO(sql_connector);
        List<Ballot> ballots = ballotDAO.getAllBallots();
        ballots.stream().forEach(ballot -> System.out.println(ballot.toString()));
        System.out.println(ballotDAO.fetchBallotById(3).toString());

        ICandidateDAO candidateDAO = new CandidateDAO(sql_connector);
        List<Candidate> candidates = candidateDAO.getAllCandidates();
        candidates.stream().forEach(c -> System.out.println(c.toString()));
        System.out.println(candidateDAO.fetchCandidateById(4).toString());
    }

}
