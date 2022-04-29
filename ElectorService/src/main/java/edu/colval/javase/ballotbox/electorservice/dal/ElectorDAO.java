package edu.colval.javase.ballotbox.electorservice.dal;

import bll.model.Elector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElectorDAO implements IElectorDAO{
    List<Elector> electors;

    public ElectorDAO(InMemoryRepository memoryRepository){
        electors = new ArrayList<>();
        memoryRepository.getElectors().stream().forEach(e -> addElector(e));
    }
    @Override
    public void addElector(Elector elector) {
        this.electors.add(elector);
    }

    @Override
    public List<Elector> getAllElectors() {
        return this.electors;
    }

    @Override
    public Optional<Elector> fetchElectorById(int electorId) {
        return this.electors.stream().filter(e -> e.getId()==electorId).findFirst();
    }

    @Override
    public void deleteElector(int electorId) {
        this.electors.remove(this.electors.stream().filter(e -> e.getId()==electorId).findFirst().get());
    }
}
