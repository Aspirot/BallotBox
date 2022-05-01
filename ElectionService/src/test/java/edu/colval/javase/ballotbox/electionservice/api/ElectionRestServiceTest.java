package edu.colval.javase.ballotbox.electionservice.api;

import edu.colval.javase.ballotbox.electionservice.bll.model.Ballot;
import edu.colval.javase.ballotbox.electionservice.dal.IBallotDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElectionRestServiceTest {


    ElectionRestService service = null;
    @BeforeEach
    void init(){
        service = new ElectionRestService();
    }

    @AfterEach
    void teardown(){
        service = null;
    }

    @Test
    void polyScan() {
        ElectionRestService spy_service=Mockito.spy(service);
        IBallotDAO spy_dao = spy_service.ballotDAO;
        doReturn(Arrays.asList(
                new Vote_DTO(3,1,1),new Vote_DTO(3,1,1),new Vote_DTO(2,1,1),new Vote_DTO(1,1,1),//1/1+1+2+3 = 7
                new Vote_DTO(1,1,2),new Vote_DTO(1,1,2),new Vote_DTO(3,1,2),new Vote_DTO(3,1,2),//2/3+3+1+1 = 8
                new Vote_DTO(2,1,3),new Vote_DTO(2,1,3),new Vote_DTO(1,1,3),new Vote_DTO(2,1,3)))//3/2+2+3+2 = 9)
                .when(spy_service).fetchVotesWithQuery(anyString());
        /*when(service.fetchVotesWithQuery(anyString())).thenReturn(Arrays.asList(
                new Vote_DTO(3,1,1),new Vote_DTO(3,1,1),new Vote_DTO(2,1,1),new Vote_DTO(1,1,1),//1/1+1+2+3 = 7
                new Vote_DTO(1,1,2),new Vote_DTO(1,1,2),new Vote_DTO(3,1,2),new Vote_DTO(3,1,2),//2/3+3+1+1 = 8
                new Vote_DTO(2,1,3),new Vote_DTO(2,1,3),new Vote_DTO(1,1,3),new Vote_DTO(2,1,3)));//3/2+2+3+2 = 9*/
        doReturn(Arrays.asList(1,2,3)).when(spy_dao).fetchBallotById(anyInt()).getCandidates();
        Integer expected = 3;

        Integer actual = spy_service.polyScan(2);

        assertEquals(expected,actual);
    }

    @Test
    void loneScan() {
        ElectionRestService spy_service=Mockito.spy(service);
        when(spy_service.fetchVotesWithQuery(anyString())).thenReturn(Arrays.asList(mock(Vote_DTO.class),mock(Vote_DTO.class),mock(Vote_DTO.class),mock(Vote_DTO.class),mock(Vote_DTO.class),mock(Vote_DTO.class)));
    }
}