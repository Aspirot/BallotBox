package edu.colval.javase.ballotbox.electionservice.dal;

import edu.colval.javase.ballotbox.electionservice.bll.model.Candidate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CandidateDAO implements ICandidateDAO{

    private I_SQL_Connector driver;

    public CandidateDAO(I_SQL_Connector connector) {
        this.driver = connector;
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        String sql = String.format("INSERT INTO Candidates (name, description, image) " +
                        "VALUES ('%s', '%s', '%s');",
                candidate.getName(),candidate.getDescription(),candidate.getImage());
        runUpdateQuery(sql);
    }

    private void runUpdateQuery(String sql) {
        try {
            Statement stmt = this.driver.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList();
        try {
            String query = "SELECT * FROM Candidates ";
            candidates = doQueryForList(query);
        } catch (SQLException ex) {
            Logger.getLogger(CandidateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }//try
        return candidates;
    }

    @Override
    public Candidate fetchCandidateById(int candidateId) {
        Candidate candidate = null;
        try {
            String query = String.format("SELECT * FROM Candidates " +
                    "WHERE id='%d'", candidateId);
            candidate = doQueryForOne(query);
        } catch (SQLException ex) {
            Logger.getLogger(CandidateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidate;
    }

    @Override
    public void deleteCandidateById(int candidateId) throws SQLException {
        String query = String.format("DELETE FROM Candidates " +
                "WHERE id='%d'", candidateId);
        Statement st = this.driver.getConnection().createStatement();
        st.executeQuery(query);
    }

    private Candidate doQueryForOne(String query) throws SQLException {
        Candidate candidate = null;
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            candidate= returnCandidateFromResult(result);
        }
        return candidate;
    }
    private List<Candidate> doQueryForList(String query) throws SQLException {
        List<Candidate> candidates = new ArrayList<>();
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            candidates.add(returnCandidateFromResult(result));
        }
        return candidates;
    }

    private Candidate returnCandidateFromResult(ResultSet result) throws SQLException {
        int candidateId = result.getInt("id");
        String name = result.getString("name");
        String description = result.getString("description");
        String image = result.getString("image");
        return new Candidate(candidateId,name,description,image);
    }
}
