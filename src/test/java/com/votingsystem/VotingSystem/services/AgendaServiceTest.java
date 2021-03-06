package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.Agenda;
import com.votingsystem.VotingSystem.interfaces.IAgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceTest {
    private static final String CANNOT_CREATE_ERROR_MESSAGE  = "Cannot create new agenda to vote without a question";

    @Mock
    private IAgendaRepository agendaRepository;

    @InjectMocks
    private AgendaService agendaService;

    private Agenda agenda;

    @BeforeEach
    public void setup() {
        agenda = new Agenda("id", "question", new Date(), new Date());
    }

    @Test
    @DisplayName("should create an Agenda with a valid agenda input")
    public void givenValidAgenda_whenCreate_thenReturnAnValidObject() {
        when(agendaRepository.save(agenda)).thenReturn(agenda);
        try {
            var savedAgenda = agendaService.create(agenda);
            assertThat(savedAgenda).isNotNull();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }

    }

    @Test
    @DisplayName("should throw an error if an agenda question is an empty string")
    public void givenAgendaWithEmptyQuestion_whenCreate_thenThrowAnException() {
        agenda.setQuestion("");
        assertThatThrownBy(() -> agendaService.create(agenda)).withFailMessage(CANNOT_CREATE_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("should throw an error if an agenda question is a string with only whitespaces")
    public void givenAgendaWithFullWhitespacesQuestion_whenCreate_thenThrowAnException() {
        agenda.setQuestion("         ");
        assertThatThrownBy(() -> agendaService.create(agenda)).withFailMessage(CANNOT_CREATE_ERROR_MESSAGE);
    }
}
