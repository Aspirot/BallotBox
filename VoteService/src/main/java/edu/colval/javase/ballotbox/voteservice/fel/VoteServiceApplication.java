package edu.colval.javase.ballotbox.voteservice.fel;

import edu.colval.javase.ballotbox.voteservice.api.VoteRestService;
import edu.colval.javase.ballotbox.voteservice.bll.model.Vote;
import edu.colval.javase.ballotbox.voteservice.dal.Alwaysdata_SQL_Connector;
import edu.colval.javase.ballotbox.voteservice.dal.IVoteDAO;
import edu.colval.javase.ballotbox.voteservice.dal.I_SQL_Connector;
import edu.colval.javase.ballotbox.voteservice.dal.VoteDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackageClasses = VoteRestService.class)
public class VoteServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(VoteServiceApplication.class, args);
        //run_usingOnlineRepository();
    }

    private static void run_usingOnlineRepository() {
        VoteDAO voteDAO = new VoteDAO(new Alwaysdata_SQL_Connector());
        voteDAO.createVote(new Vote(LocalDate.parse("2002-02-06"), 1, 1, 1, 1));
        List<Vote> voteList = voteDAO.findAllVotes();
        voteList.stream().forEach(vote -> System.out.println(vote.toString()));
    }

}
