package edu.colval.javase.ballotbox.electionservice.dal;

import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IBallotDAO {
    void addBallot(Ballot ballot);
    void addElectorToBallot(int electorId, int pollId);
    void addCandidateToBallot(int candidateId, int pollId);
    List<Ballot> getAllBallots();
    Ballot fetchBallotById(int ballotId);
    void deleteBallotById(int ballotId) throws SQLException;
}
