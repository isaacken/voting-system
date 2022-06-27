package com.votingsystem.VotingSystem.controllers;

import com.votingsystem.VotingSystem.entities.Agenda;
import com.votingsystem.VotingSystem.interfaces.IAgendaService;
import com.votingsystem.VotingSystem.requests.CreateAgendaRequest;
import com.votingsystem.VotingSystem.responses.CreateAgendaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agenda")
public class AgendaController {
    private IAgendaService agendaService;

    @Autowired
    public AgendaController(IAgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping
    public ResponseEntity<Object> createAgenda(@RequestBody CreateAgendaRequest request) {
        var agenda = new Agenda();
        agenda.setQuestion(request.getQuestion());

        Agenda createdAgenda;

        try {
            createdAgenda = agendaService.create(agenda);
        } catch (Exception exception) {
            return ResponseEntity.status(400).body("Invalid payload");
        }

        var response = new CreateAgendaResponse(createdAgenda);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
