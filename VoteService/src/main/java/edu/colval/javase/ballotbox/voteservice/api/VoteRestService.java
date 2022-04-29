package edu.colval.javase.ballotbox.voteservice.api;

import edu.colval.javase.ballotbox.voteservice.dal.IVoteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote_service/api")
public class VoteRestService {
    @Autowired
    private IVoteDAO dao;

    
}
