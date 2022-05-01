package edu.colval.javase.ballotbox.electorservice.dal;

import edu.colval.javase.ballotbox.electorservice.bll.model.Elector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ElectorDAO implements IElectorDAO{
    private I_SQL_Connector driver;

    public ElectorDAO(I_SQL_Connector connector) {
        driver = connector;
    }

    @Override
    public void addElector(Elector elector) {
        String sql = String.format("INSERT INTO Electors (login, password, weight, email) " +
                        "VALUES ('%s', '%s', '%d', '%s');",
                elector.getLogin(), elector.getPassword(), elector.getWeight(), elector.getEmail());
        runUpdateQuery(sql);
    }

    @Override
    public List<Elector> getAllElectors() {
        List<Elector> electors = new ArrayList();
        try {
            String query = "SELECT * FROM Electors ";
            electors = doQueryForList(query);
        } catch (SQLException ex) {
            Logger.getLogger(ElectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return electors;
    }

    @Override
    public Elector fetchElectorById(int searchedElectorId) {
        Elector elector = null;
        try {
            String query = String.format("SELECT * FROM Electors " +
                    "WHERE id='%d'", searchedElectorId);
            elector = doQueryForOne(query);
        } catch (SQLException ex) {
            Logger.getLogger(ElectorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elector;
    }

    @Override
    public void deleteElector(int searchedElectorId) throws SQLException {
        String query = String.format("DELETE FROM Electors " +
                "WHERE id='%d'", searchedElectorId);
        Statement st = this.driver.getConnection().createStatement();
        st.executeQuery(query);
    }

    private void runUpdateQuery(String sql) {
        try {
            Statement stmt = this.driver.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<Elector> doQueryForList(String query) throws SQLException {
        List<Elector> electors = new ArrayList<>();
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            electors.add(returnElectorFromResult(result));
        }
        return electors;
    }

    private Elector doQueryForOne(String query) throws SQLException {
        Elector elector = null;
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            elector=returnElectorFromResult(result);
        }
        return elector;
    }

    private Elector returnElectorFromResult(ResultSet result) throws SQLException {
        int electorId = result.getInt("id");
        String login = result.getString("login");
        String password = result.getString("password");
        int weight = result.getInt("weight");
        String email = result.getString("email");
        return new Elector(electorId, login, password, weight, email);
    }


}
