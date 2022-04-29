package edu.colval.javase.ballotbox.electionservice.dal;

import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;

import java.util.List;
import java.util.Optional;

public interface IBallotDAO {
    void addBallot(Ballot ballot);
    List<Ballot> getAllBallots();
    Optional<Ballot> fetchBallotById(int ballotId);
    //nous n'avons pas trouvez de update qui serait nécessaire pour les ballots
    void deleteBallot(int ballotId);
}
