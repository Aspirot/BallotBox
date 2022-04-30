package edu.colval.javase.ballotbox.electionservice.bll.control;

import edu.colval.javase.ballotbox.electionservice.dal.*;

public class ElectionController {
    ICandidateDAO candidateDAO;
    IBallotDAO ballotDAO;

    public ElectionController(I_SQL_Connector connector) {
        this.candidateDAO = new CandidateDAO(connector);
        this.ballotDAO = new BallotDAO(connector);
    }


}
