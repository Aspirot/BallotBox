package edu.colval.javase.ballotbox.voteservice.fel;

import edu.colval.javase.ballotbox.voteservice.bll.model.Vote;
import edu.colval.javase.ballotbox.voteservice.dal.VoteDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteServiceApplication.class, args);
    }

    /*public static void main(String[] args) {
        VoteDAO voteDAO = new VoteDAO();
        voteDAO.createVote(LocalDate.parse("2002-02-06"), 1, 1, 1, 1);
        List<Vote> voteList = voteDAO.findAllVotes();
        voteList.stream().forEach(vote -> System.out.println(vote.toString()));
    }*/
}
