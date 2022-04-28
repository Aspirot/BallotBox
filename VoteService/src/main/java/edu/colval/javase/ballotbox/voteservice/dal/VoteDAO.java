package edu.colval.javase.ballotbox.voteservice.dal;

import edu.colval.javase.ballotbox.voteservice.bll.model.Vote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoteDAO {
        private static VoteDAO instance = null;
        private I_SQL_Connector driver;

        public VoteDAO() {
            // driver = IBMDB2_SQL_Driver();
            driver = new Alwaysdata_SQL_Connector();
        }

        /*public Vote findClientByUsernameAndPassword(String username, String password) {
            String query = String.format("SELECT * FROM Client " +
                    "WHERE username='%s' and password='%s'", username, password);
            Client client = null;
            try {
                Statement st = this.driver.getConnection().createStatement();
                ResultSet result = st.executeQuery(query);

                while (result.next()) {
                    int clientId = result.getInt(1);
                    String firstname = result.getString("firstname");
                    String lastname = result.getString("lastname");
                    client = new Client(clientId, firstname, lastname, username, password);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }//try
            return client;

        }*/
        public List<Vote> findAllVotes() {
            List<Vote> votes = new ArrayList();
            try {
                Statement st = this.driver.getConnection().createStatement();
                String query = "SELECT * FROM Votes ";
                ResultSet result = st.executeQuery(query);

                while (result.next()) {
                    int voteId = result.getInt("id");
                    LocalDate when =LocalDate.parse(result.getString("dateVoteMade"));
                    int rank = result.getInt("rank");
                    int pollId = result.getInt("pollId");
                    int pollSubjectId = result.getInt("pollSubjectId");
                    int electorId = result.getInt("electorId");
                    votes.add(new Vote(voteId,when, rank, pollId, pollSubjectId, electorId));
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }//try
            return votes;
        }

        private void runUpdateQuery(String sql) {
            try {
                Statement stmt = this.driver.getConnection().createStatement();
                stmt.executeUpdate(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
}
