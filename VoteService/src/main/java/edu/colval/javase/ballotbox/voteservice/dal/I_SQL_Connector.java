package edu.colval.javase.ballotbox.voteservice.dal;

import java.sql.Connection;

public interface I_SQL_Connector {
    public Connection connect();
    public Connection getConnection();
}
