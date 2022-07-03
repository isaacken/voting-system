package com.votingsystem.VotingSystem.controllers;

import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionService;
import com.votingsystem.VotingSystem.requests.StartSessionRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voting-session")
public class VotingSessionController {
    private final IVotingSessionService votingSessionService;

    @Autowired
    public VotingSessionController(IVotingSessionService votingSessionService) {
        this.votingSessionService = votingSessionService;
    }

    @PostMapping("/start")
    public ResponseEntity<?> startSession(@RequestBody StartSessionRequest request) {
        var votingSession = new VotingSession();

        if (request.getSessionDurationInSeconds() != 0) {
            votingSession.setSessionDurationInSeconds(request.getSessionDurationInSeconds());
        }

        votingSession.setAgendaId(new ObjectId(request.getAgendaId()));

        VotingSession createdVotingSession;
        try {
            createdVotingSession = votingSessionService.startSession(votingSession);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdVotingSession);
    }
}
