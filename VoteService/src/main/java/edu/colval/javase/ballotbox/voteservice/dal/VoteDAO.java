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

public class VoteDAO implements IVoteDAO {
    private I_SQL_Connector driver;

    public VoteDAO() {
        driver = new Alwaysdata_SQL_Connector();
    }

    @Override
    public void createVote( LocalDate when, int rank, int pollId, int pollSubjectId, int electorId){
        String sql = String.format("INSERT INTO Votes (dateVoteMade, rank, pollId, pollSubjectId, electorId) " +
            "VALUES ('%s', '%d', '%d', '%d', '%d');",
            when.toString().substring(0, 10), rank, pollId, pollSubjectId, electorId);
        runUpdateQuery(sql);
    }

    @Override
    public List<Vote> findAllVotes() {
        List<Vote> votes = new ArrayList();
        try {
            String query = "SELECT * FROM Votes ";
            votes = doQueryForList(query);
        } catch (SQLException ex) {
            Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }//try
        return votes;
    }

    @Override
    public List<Vote> getAllVotesForBallot(int searchedPollId) {
        List<Vote> votes = new ArrayList();
        try {
            String query = String.format("SELECT * FROM Votes " +
                "WHERE pollId='%d'", searchedPollId);
            votes = doQueryForList(query);
        } catch (SQLException ex) {
            Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votes;
    }

    @Override
    public Vote fetchVoteById(int searchedVoteId) {
        Vote vote = null;
        try {
            String query = String.format("SELECT * FROM Votes " +
                "WHERE voteId='%d'", searchedVoteId);
            vote = doQueryForOne(query);
        } catch (SQLException ex) {
            Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vote;
    }

    @Override
    public Vote fetchVoteByCandidateId_PollId_Rank(int searchedCandidateId, int searchedPollId, int searchRank) {
        Vote vote = null;
        try {
            String query = String.format("SELECT * FROM Votes " +
                "WHERE voteId='%d' AND pollId='%d' AND rank='%d'", searchedCandidateId, searchedPollId, searchRank);
            vote = doQueryForOne(query);
        } catch (SQLException ex) {
            Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vote;
    }

    @Override
    public Vote fetchVoteByElectorId_PollId_Rank(int searchedElectorId, int searchedPollId, int searchedRank) {
        Vote vote = null;
        try {
            String query = String.format("SELECT * FROM Votes " +
                "WHERE electorId='%d' AND pollId='%d' AND rank='%d'", searchedElectorId, searchedPollId, searchedRank);
            vote = doQueryForOne(query);
        } catch (SQLException ex) {
            Logger.getLogger(VoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vote;
    }

    private Vote doQueryForOne(String query) throws SQLException {
        Vote vote = null;
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            vote=returnVoteFromResult(result);
        }
        return vote;
    }
    private List<Vote> doQueryForList(String query) throws SQLException {
        List<Vote> votes = null;
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            votes.add(returnVoteFromResult(result));
        }
        return votes;
    }

    private Vote returnVoteFromResult(ResultSet result) throws SQLException {
        int voteId = result.getInt("id");
        LocalDate when =LocalDate.parse(result.getString("dateVoteMade"));
        int rank = result.getInt("rank");
        int pollId = result.getInt("pollId");
        int pollSubjectId = result.getInt("pollSubjectId");
        int electorId = result.getInt("electorId");
        return new Vote(voteId,when, rank, pollId, pollSubjectId, electorId);
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
