package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.Agenda;
import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.interfaces.IAgendaService;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionRepository;
import org.assertj.core.presentation.HexadecimalRepresentation;
import org.bson.types.ObjectId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VotingSessionServiceTest {
    @Mock
    private IVotingSessionRepository votingSessionRepository;

    @Mock
    private IAgendaService agendaService;

    @InjectMocks
    private VotingSessionService votingSessionService;

    private VotingSession votingSession;
    private Agenda agenda;

    @BeforeEach
    public void setup() {
        agenda = new Agenda("62bd2ae7d435a23d8127f3c1", "question", new Date(), new Date());

        votingSession = new VotingSession();
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
    }

    @Test
    public void givenValidVotingSession_whenStartSession_thenReturnVotingSession() {
        when(votingSessionRepository.save(votingSession)).thenReturn(votingSession);
        when(agendaService.findById(votingSession.getAgendaId().toString())).thenReturn(Optional.ofNullable(agenda));

        VotingSession createdVotingSession;

        try {
            createdVotingSession = votingSessionService.startSession(votingSession);
        } catch (Exception exception) {
            fail(exception.getMessage());
            return;
        }

        assertThat(createdVotingSession).isNotNull();
    }

    @Test
    public void givenInvalidVotingSession_whenStartSession_thenThrowException() {
        votingSession.setSessionDurationInSeconds(0);
        votingSession.setAgendaId(null);

        try {
            votingSessionService.startSession(votingSession);
            fail("Expected exception");
        } catch (Exception exception) {
            assertThat(exception.getMessage()).isEqualTo("Invalid voting session");
        }
    }

}
