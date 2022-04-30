package edu.colval.javase.ballotbox.electorservice.dal;

import edu.colval.javase.ballotbox.electorservice.bll.model.Elector;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IElectorDAO {
    void addElector(Elector elector);
    List<Elector> getAllElectors();
<<<<<<< HEAD
    Elector fetchElectorById(int electorId);
    void deleteElector(int electorId) throws SQLException;
=======
    Optional<Elector> fetchElectorById(int electorId);
    void deleteElector(int electorId);
>>>>>>> 28cc826243a7f64a56cede47c14d30aab534693a
}
