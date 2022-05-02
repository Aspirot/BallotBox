package edu.colval.javase.ballotbox.electionservice.bll.control;

import edu.colval.javase.ballotbox.electionservice.api.Vote_DTO;
import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;
import edu.colval.javase.ballotbox.electionservice.dal.Alwaysdata_SQL_Connector;
import edu.colval.javase.ballotbox.electionservice.dal.BallotDAO;
import edu.colval.javase.ballotbox.electionservice.dal.IBallotDAO;
import edu.colval.javase.ballotbox.electionservice.dal.I_SQL_Connector;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElectionControllerTest {
    ElectionController electionController;
    Ballot mockedBallot;
    IBallotDAO mockedDAO;
    List<Vote_DTO> testVotes;
    @BeforeEach
    void init(){
        testVotes = Arrays.asList(
                new Vote_DTO(3,1,1),new Vote_DTO(3,1,1),new Vote_DTO(2,1,1),new Vote_DTO(1,1,1),//1/lone:0+0+0+1=1/poly:1+1+2+3 = 7
                new Vote_DTO(1,1,2),new Vote_DTO(1,1,2),new Vote_DTO(3,1,2),new Vote_DTO(3,1,2),//2/lone:1+1+0+0=2/poly:3+3+1+1 = 8
                new Vote_DTO(2,1,3),new Vote_DTO(2,1,3),new Vote_DTO(1,1,3),new Vote_DTO(2,1,3));//3/lone:0+0+1+0=1/poly:2+2+3+2// = 9)
        mockedBallot = mock(Ballot.class);
        mockedDAO = spy(IBallotDAO.class);
    }

    @AfterEach
    void teardown(){
        electionController = null;
        testVotes = new ArrayList<>();
        mockedDAO = null;
        mockedBallot = null;
    }

    @Test
    void polyScan() {
        //arrange
        int pollId = 2;
        when(mockedDAO.fetchBallotById(Mockito.anyInt())).thenReturn(mockedBallot);
        when(mockedDAO.fetchBallotById(Mockito.anyInt()).getCandidates()).thenReturn(Arrays.asList(1,2,3));
        electionController = new ElectionController(mockedDAO);
        int expected = 3;
        //act
        int actual = this.electionController.polyScan(pollId,testVotes);
        //assert
        assertEquals(expected,actual);
    }

    @Test
    void loneScan() {
        //arrange
        int pollId = 1;
        when(mockedDAO.fetchBallotById(Mockito.anyInt())).thenReturn(mockedBallot);
        when(mockedDAO.fetchBallotById(Mockito.anyInt()).getCandidates()).thenReturn(Arrays.asList(1,2,3));
        electionController = new ElectionController(mockedDAO);
        testVotes = testVotes.stream().filter(v -> v.getRank()==1).collect(Collectors.toList());
        int expected = 2;
        //act
        int actual = this.electionController.loneScan(pollId,testVotes);
        //assert
        assertEquals(expected,actual);
    }
}