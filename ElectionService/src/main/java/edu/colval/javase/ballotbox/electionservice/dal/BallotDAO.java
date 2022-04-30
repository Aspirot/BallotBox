package edu.colval.javase.ballotbox.electionservice.dal;

import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BallotDAO implements IBallotDAO{

    private I_SQL_Connector driver;

    public BallotDAO(I_SQL_Connector connector){
        this.driver = connector;
    }

    @Override
    public void addBallot(Ballot ballot) {
        String sql = String.format("INSERT INTO Ballots (title, start, end, isPublic, isAnonymous, ownerId, forumId) " +
                        "VALUES ('%s', '%s', '%s', '%b', '%b', '%d', '%d');",
                ballot.getTitle(), ballot.getStart().toString().substring(0, 10), ballot.getEnd().toString().substring(0, 10), ballot.getPublic(),ballot.getAnonymous(),ballot.getOwnerId(),ballot.getForumId());
        runUpdateQuery(sql);

        ballot.getCandidates().stream().forEach(cId -> {String balCanSql =
                String.format("INSERT INTO BallotCandidate (pollId, candidateId) " +
                "VALUES ('%d', '%d');", ballot.getId(),cId);
        runUpdateQuery(balCanSql);});

        ballot.getElectors().stream().forEach(eId -> {String balElecSql =
                String.format("INSERT INTO BallotElector (pollId, electorId) " +
                        "VALUES ('%d', '%d');", ballot.getId(),eId);
            runUpdateQuery(balElecSql);});
    }

    @Override
    public void addElectorToBallot(int electorId, int pollId) {
        String balElecSql =
                String.format("INSERT INTO BallotElector (pollId, electorId) " +
                        "VALUES ('%d', '%d');", pollId,electorId);
        runUpdateQuery(balElecSql);
    }

    @Override
    public void addCandidateToBallot(int candidate, int pollId) {
        String balCanSql =
                String.format("INSERT INTO BallotCandidate (pollId, candidateId) " +
                        "VALUES ('%d', '%d');", pollId,candidate);
        runUpdateQuery(balCanSql);
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
    public List<Ballot> getAllBallots() {
        List<Ballot> ballots = new ArrayList();
        try {
            String query = "SELECT * FROM Ballots ";
            ballots = doQueryForList(query);
        } catch (SQLException ex) {
            Logger.getLogger(BallotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }//try

        ballots.stream().forEach(b -> {
            String candidateIdListQuery = String.format("SELECT * FROM BallotCandidate " +
                    "WHERE pollId='%d'",b.getId());
            String electorIdListQuery = String.format("SELECT * FROM BallotElector " +
                    "WHERE pollId='%d'",b.getId());
            try {
                b.setCandidates(doCandidateOrElectorIdQuery(candidateIdListQuery,"candidateId"));
                b.setElectors(doCandidateOrElectorIdQuery(electorIdListQuery,"electorId"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        return ballots;
    }

    @Override
    public Ballot fetchBallotById(int ballotId) {
        Ballot ballot = null;
        try {
            String query = "SELECT * FROM Ballots " +
                    "WHERE id="+ballotId;
            ballot = doQueryForOne(query);
        } catch (SQLException ex) {
            Logger.getLogger(BallotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }//try

        String candidateIdListQuery = String.format("SELECT * FROM BallotCandidate " +
                "WHERE pollId='%d'",ballotId);
        String electorIdListQuery = String.format("SELECT * FROM BallotElector " +
                "WHERE pollId='%d'",ballotId);
        try {
            ballot.setCandidates(doCandidateOrElectorIdQuery(candidateIdListQuery,"candidateId"));
            ballot.setElectors(doCandidateOrElectorIdQuery(electorIdListQuery,"electorId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ballot;
    }

    @Override
    public void deleteBallotById(int ballotId) throws SQLException {
        String query = String.format("DELETE FROM Ballots " +
                "WHERE id='%d'", ballotId);
        Statement st = this.driver.getConnection().createStatement();
        st.executeQuery(query);
        query = String.format("DELETE FROM BallotCandidate " +
                "WHERE pollId='%d'", ballotId);
        st.executeQuery(query);
        query = String.format("DELETE FROM BallotElector " +
                "WHERE pollId='%d'", ballotId);
        st.executeQuery(query);
    }

    private Ballot doQueryForOne(String query) throws SQLException {
        Ballot ballot = null;
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            ballot=returnBallotFromResult(result);
        }
        return ballot;
    }
    private List<Ballot> doQueryForList(String query) throws SQLException {
        List<Ballot> ballots = new ArrayList<>();
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            ballots.add(returnBallotFromResult(result));
        }

        return ballots;
    }

    private List<Integer> doCandidateOrElectorIdQuery(String query,String personFunction) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        Statement st = this.driver.getConnection().createStatement();
        ResultSet result = st.executeQuery(query);

        while (result.next()) {
            ids.add(result.getInt(personFunction));
        }
        return ids;
    }

    private Ballot returnBallotFromResult(ResultSet result) throws SQLException {
        int ballotId = result.getInt("id");
        String title =result.getString("title");
        LocalDate start = LocalDate.parse(result.getString("start"));
        LocalDate end = LocalDate.parse(result.getString("end"));
        boolean isPublic = result.getBoolean("isPublic");
        boolean isAnonymous = result.getBoolean("isAnonymous");
        int ownerId = result.getInt("ownerId");
        int forumId = result.getInt("forumId");
        return new Ballot(ballotId,title,start,end,isPublic,isAnonymous,ownerId,forumId);
    }



}
