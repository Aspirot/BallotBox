package edu.colval.javase.ballotbox.electionservice.dal;

import edu.colval.javase.ballotbox.electionservice.bll.model.Candidate;

import java.sql.SQLException;
import java.util.List;

public interface ICandidateDAO {
    void saveCandidate(Candidate candidate);
    List<Candidate> getAllCandidates();
    Candidate fetchCandidateById(int candidateId);
    void deleteCandidateById(int candidateId) throws SQLException;
}
