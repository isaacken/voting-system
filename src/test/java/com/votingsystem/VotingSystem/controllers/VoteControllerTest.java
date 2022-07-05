package com.votingsystem.VotingSystem.controllers;


import com.votingsystem.VotingSystem.entities.Vote;
import com.votingsystem.VotingSystem.enums.VoteValue;
import com.votingsystem.VotingSystem.exceptions.InvalidRequestException;
import com.votingsystem.VotingSystem.exceptions.ResourceNotFoundException;
import com.votingsystem.VotingSystem.interfaces.IVoteService;
import com.votingsystem.VotingSystem.requests.VoteAgendaRequest;
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
public class VoteControllerTest {
    @Mock
    private IVoteService voteService;

    @InjectMocks
    private VoteController voteController;

    private Vote vote;

    @BeforeEach
    public void setup() {
        vote = new Vote();
        vote.setVoterId("123456789");
        vote.setVotingSessionId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setVoteValue(VoteValue.YES);
    }

    @Test
    @DisplayName("should save a vote when receiving valid data")
    public void givenValidVote_whenSaveVote_thenReturnVote() {
        try {
            when(voteService.voteAgenda(any(Vote.class))).thenReturn(vote);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        VoteAgendaRequest voteAgendaRequest = new VoteAgendaRequest();
        voteAgendaRequest.setVoterId("123456789");
        voteAgendaRequest.setAgendaId("62bd2ae7d435a23d8127f3c1");
        voteAgendaRequest.setVoteValue(VoteValue.YES.toString());

        ResponseEntity<?> response = voteController.voteAgenda(voteAgendaRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("should throw exception when receiving invalid data")
    public void givenInvalidVote_whenSaveVote_thenThrowException() {
        try {
            when(voteService.voteAgenda(any(Vote.class))).thenThrow(new InvalidRequestException("Invalid vote"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        VoteAgendaRequest voteAgendaRequest = new VoteAgendaRequest();
        voteAgendaRequest.setVoterId("123456789");
        voteAgendaRequest.setAgendaId("62bd2ae7d435a23d8127f3c1");
        voteAgendaRequest.setVoteValue(VoteValue.YES.toString());

        ResponseEntity<?> response = voteController.voteAgenda(voteAgendaRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}
