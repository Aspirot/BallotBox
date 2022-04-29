package edu.colval.javase.ballotbox.electorservice.dal;

import bll.model.Elector;

import java.util.List;
import java.util.Optional;

public interface IElectorDAO {
    void addElector(Elector elector);
    List<Elector> getAllElectors();
    Optional<Elector> fetchElectorById(int electorId);
    //nous n'avons pas trouvez de update qui serait nécessaire pour les électeurs
    void deleteElector(int electorId);
}
