package edu.colval.javase.ballotbox.electorservice.dal;

import bll.model.Elector;

import java.util.List;
import java.util.Optional;

public interface IElectorDAO {
    void addElector(Elector elector);
    List<Elector> getAllElectors();
    Optional<Elector> fetchElectorById(int electorId);
    void deleteElector(int electorId);
}
