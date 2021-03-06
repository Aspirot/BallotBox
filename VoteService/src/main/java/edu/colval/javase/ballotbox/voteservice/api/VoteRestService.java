package edu.colval.javase.ballotbox.voteservice.api;

import edu.colval.javase.ballotbox.voteservice.bll.model.Vote;
import edu.colval.javase.ballotbox.voteservice.dal.Alwaysdata_SQL_Connector;
import edu.colval.javase.ballotbox.voteservice.dal.IVoteDAO;
import edu.colval.javase.ballotbox.voteservice.dal.I_SQL_Connector;
import edu.colval.javase.ballotbox.voteservice.dal.VoteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/voteService")
public class VoteRestService {

    I_SQL_Connector sql_connector = new Alwaysdata_SQL_Connector();
    private IVoteDAO voteDAO = new VoteDAO(sql_connector);

    @PostMapping("/createVote")
    public Vote createVote(@RequestBody Vote newVote){
        this.voteDAO.createVote(newVote);
        return this.voteDAO.fetchVoteById(newVote.getId());
    }

    @RequestMapping("/findAllVotes")
    public List<Vote> searchAllVotes(){
        return this.voteDAO.findAllVotes();
    }

    @RequestMapping("/findVotesByBallot/{ballotId}")
    public List<Vote> searchVotesByBallotId(@PathVariable("ballotId") int bId){
        return this.voteDAO.getAllVotesForBallot(bId);
    }
    @RequestMapping("/findVotesByPollAndRank/{ballotId}/{rank}")
    public List<Vote> searchVotesByPollAndRank(@PathVariable("ballotId") int bId, @PathVariable("rank") int rank){
        return this.voteDAO.fetchVotesByPollIdAndRank(bId,rank);
    }


    @RequestMapping("/findVoteByCandidatePollAndRank/{candidateId}/{pollId}/{rank}")
    public List<Vote> searchVotesByCandidateId_PollId_Rank(@PathVariable("candidateId") int cId, @PathVariable("pollId") int pId, @PathVariable("rank") int rank){

        return this.voteDAO.fetchVotesByCandidateId_PollId_Rank(cId,pId,rank);
    }
            //fetchVoteByElectorId_PollId_Rank
    @RequestMapping("/findVoteByElectorPollAndRank/{electorId}/{pollId}/{rank}")
    public Vote searchVoteByElectorId_PollId_Rank(@PathVariable("electorId") int eId,@PathVariable("pollId") int pId, @PathVariable("rank") int rank){
        return this.voteDAO.fetchVoteByElectorId_PollId_Rank(eId,pId,rank);
    }

    @DeleteMapping("/deleteVote/{id}")
    public Vote deletePostById(@PathVariable("id") int id) throws SQLException {
        Vote deletedVote = this.voteDAO.fetchVoteById(id);
        this.voteDAO.deleteVoteById(id);
        return deletedVote;
    }

    @RequestMapping("/findVoteByElectorAndPoll/{electorId}/{pollId}")
    public List<Vote> searchVoteByElectorId_PollId(@PathVariable("electorId") int eId,@PathVariable("pollId") int pId){
        return this.voteDAO.getVotesForElectorAndBallot(eId,pId);
    }

}
