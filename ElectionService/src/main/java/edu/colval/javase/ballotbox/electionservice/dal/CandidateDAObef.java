package edu.colval.javase.ballotbox.electionservice.dal;

import bll.model.Candidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandidateDAObef implements ICandidateDAO{
    private List<Candidate> candidates;

    public CandidateDAObef(InMemoryRepository memoryRepository) {
        this.candidates = new ArrayList<>();
        memoryRepository.getCandidates().stream().forEach(c -> savecandidate(c));
    }

    @Override
    public void savecandidate(Candidate candidate) {
        this.candidates.add(candidate);
    }

    @Override
    public List<Candidate> getAllCanditates() {
        return this.candidates;
    }

    @Override
    public Optional<Candidate> fetchCandidateById(int candidateId) {
        return this.candidates.stream().filter(c -> c.getId()==candidateId).findFirst();
    }

    @Override
    public void deleteCanditate(int candidateId) {
        this.candidates.remove(this.candidates.stream().filter(c -> c.getId()==candidateId).findFirst().get());
    }
}
