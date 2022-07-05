package com.votingsystem.VotingSystem.controllers;

import com.votingsystem.VotingSystem.entities.Vote;
import com.votingsystem.VotingSystem.enums.VoteValue;
import com.votingsystem.VotingSystem.exceptions.InvalidRequestException;
import com.votingsystem.VotingSystem.exceptions.ResourceNotFoundException;
import com.votingsystem.VotingSystem.interfaces.IVoteService;
import com.votingsystem.VotingSystem.requests.VoteAgendaRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vote")
public class VoteController {
    private final IVoteService voteService;

    @Autowired
    public VoteController(IVoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Object> voteAgenda(@RequestBody VoteAgendaRequest request) {
        var vote = new Vote();
        vote.setAgendaId(new ObjectId(request.getAgendaId()));
        vote.setVoterId(request.getVoterId());
        try {
            vote.setVoteValue(VoteValue.valueOf(request.getVoteValue()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vote value should be YES or NO");
        }

        try {
            voteService.voteAgenda(vote);
        } catch (InvalidRequestException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch (ResourceNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
