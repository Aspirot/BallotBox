package edu.colval.javase.ballotbox.electionservice.dal;

import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IBallotDAO {
    void addBallot(Ballot ballot);
    List<Ballot> getAllBallots();
    Ballot fetchBallotById(int ballotId);
    void deleteBallotById(int ballotId) throws SQLException;
}
