package com.votingsystem.VotingSystem.controllers;

import com.votingsystem.VotingSystem.entities.Agenda;
import com.votingsystem.VotingSystem.interfaces.IAgendaService;
import com.votingsystem.VotingSystem.requests.CreateAgendaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendaControllerTest {
    @Mock
    private IAgendaService agendaService;

    @InjectMocks
    private AgendaController agendaController;

    private Agenda agenda;

    @BeforeEach
    public void setup() {
        agenda = new Agenda("id", "question", new Date(), new Date());
    }

    @Test
    @DisplayName("should return status 201 CREATED when receiving a valid Agenda creation request")
    void givenValidCreateAgendaRequest_whenCreated_thenReturnHttpCreated() throws Exception {
        when(agendaService.create(any(Agenda.class))).thenReturn(agenda);

        var payload = new CreateAgendaRequest("question");
        var response = agendaController.createAgenda(payload);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("should return status 400 BAD REQUEST when receiving an Agenda creation request with empty question")
    void givenEmptyQuestionCreateAgendaRequest_whenRequested_thenReturnHttpBadRequest() throws Exception {
        when(agendaService.create(any(Agenda.class))).thenThrow(new Exception("Cannot create new agenda to vote without a question"));

        var payload = new CreateAgendaRequest();
        var response = agendaController.createAgenda(payload);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}