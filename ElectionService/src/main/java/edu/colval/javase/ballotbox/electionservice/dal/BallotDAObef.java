package edu.colval.javase.ballotbox.electionservice.dal;

import bll.model.Ballot;
import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;

import java.util.List;
import java.util.Optional;

public class BallotDAObef implements IBallotDAO{

    private I_SQL_Connector driver;
    List<Ballot> ballots;//polls

    public BallotDAObef(){
        this.driver = new Alwaysdata_SQL_Connector();
    }
    @Override
    public void addBallot(Ballot ballot) {
        return;
    }

    @Override
    public List<Ballot> getAllBallots() {
        return null;
    }

    @Override
    public Optional<Ballot> fetchBallotById(int ballotId) {
        return this.ballots.stream().filter(b -> b.getId()==ballotId).findFirst();
    }

    @Override
    public void deleteBallot(int ballotId) {
        this.ballots.remove(this.ballots.stream().filter(b -> b.getId()==ballotId).findFirst().get());
    }
}
