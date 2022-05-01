package edu.colval.javase.ballotbox.electorservice.dal;

import edu.colval.javase.ballotbox.electorservice.bll.model.Elector;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IElectorDAO {
    void addElector(Elector elector);
    List<Elector> getAllElectors();
    void addElectorToBallot(int electorId, int pollId);
    Elector fetchElectorById(int electorId);
    void deleteElector(int electorId) throws SQLException;
}
