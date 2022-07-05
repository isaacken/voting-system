package com.votingsystem.VotingSystem.controllers;

import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.interfaces.IAgendaService;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionService;
import com.votingsystem.VotingSystem.requests.StartSessionRequest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VotingSessionControllerTest {
    @Mock
    private IVotingSessionService votingSessionService;

    @InjectMocks
    private VotingSessionController votingSessionController;

    private VotingSession votingSession;

    @BeforeEach
    public void setup() {
        votingSession = new VotingSession();
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        votingSession.setId("62bd2ae7d435a23d8127f3c1");
    }

    @Test
    @DisplayName("should start a session when receiving valid data")
    public void givenValidVotingSession_whenStartSession_thenReturnVotingSession() {
        try {
            when(votingSessionService.startSession(any(VotingSession.class))).thenReturn(votingSession);
        } catch (Exception exception) {
            fail(exception.getMessage());
            return;
        }

        StartSessionRequest startSessionRequest = new StartSessionRequest();
        startSessionRequest.setSessionDurationInSeconds(votingSession.getSessionDurationInSeconds());
        startSessionRequest.setAgendaId(votingSession.getAgendaId().toString());

        ResponseEntity<?> response = votingSessionController.startSession(startSessionRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("should return error when receiving invalid data")
    public void givenInvalidVotingSession_whenStartSession_thenReturnError() {
        try {
            when(votingSessionService.startSession(any(VotingSession.class))).thenThrow(new IllegalArgumentException("Invalid voting session"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        StartSessionRequest startSessionRequest = new StartSessionRequest();
        startSessionRequest.setSessionDurationInSeconds(votingSession.getSessionDurationInSeconds());
        startSessionRequest.setAgendaId(votingSession.getAgendaId().toString());

        ResponseEntity<?> response = votingSessionController.startSession(startSessionRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}
