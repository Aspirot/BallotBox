package edu.colval.javase.ballotbox.electionservice.dal;

import bll.model.Candidate;
import edu.colval.javase.ballotbox.electionservice.bll.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface ICandidateDAO {
    void savecandidate(Candidate candidate);
    List<Candidate> getAllCanditates();
    Optional<Candidate> fetchCandidateById(int candidateId);
    void deleteCanditate(int candidateId);
}
