package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.Vote;
import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.enums.VoteValue;
import com.votingsystem.VotingSystem.exceptions.InvalidRequestException;
import com.votingsystem.VotingSystem.exceptions.ResourceNotFoundException;
import com.votingsystem.VotingSystem.interfaces.IVoteRepository;
import com.votingsystem.VotingSystem.interfaces.IVoterValidationService;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {
    @Mock
    IVoteRepository voteRepository;

    @Mock
    IVotingSessionRepository votingSessionRepository;

    @Mock
    IVoterValidationService voterValidationService;

    @InjectMocks
    VoteService voteService;

    private Vote vote;
    private VotingSession votingSession;

    @BeforeEach
    public void setup() {
        vote = new Vote();
        vote.setVoterId("123456789");
        vote.setVotingSessionId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setVoteValue(VoteValue.YES);

        votingSession = new VotingSession();
        votingSession.setId("62bd2ae7d435a23d8127f3c1");
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
    }

    @Test
    @DisplayName("should save a vote when receiving valid data")
    public void givenValidVote_whenSaveVote_thenReturnVote() {
        when(voteRepository.save(vote)).thenReturn(vote);
        when(votingSessionRepository.findActiveVotingSessionForAgenda(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(votingSession));
        when(voterValidationService.isValidVoter(vote.getVoterId())).thenReturn(true);

        Vote createdVote;

        try {
            createdVote = voteService.voteAgenda(vote);
        } catch (Exception exception) {
            fail(exception.getMessage());
            return;
        }

        assertThat(createdVote).isEqualTo(vote);
    }

    @Test
    @DisplayName("should throw an exception when voter is not valid")
    public void givenInvalidVoter_whenSaveVote_thenThrowException() {
        when(voterValidationService.isValidVoter(vote.getVoterId())).thenReturn(false);

        vote.setVoterId("123456789");
        vote.setVotingSessionId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setVoteValue(VoteValue.YES);

        votingSession.setId("62bd2ae7d435a23d8127f3c1");
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));

        try {
            voteService.voteAgenda(vote);
            fail("Expected exception");
        } catch (ResourceNotFoundException exception) {
            assertThat(exception.getMessage()).isEqualTo("Voter is not valid");
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    @DisplayName("should throw an exception when voting session is not active")
    public void givenInvalidVotingSession_whenSaveVote_thenThrowException() {
        when(votingSessionRepository.findActiveVotingSessionForAgenda(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        when(voterValidationService.isValidVoter(vote.getVoterId())).thenReturn(true);

        vote.setVoterId("123456789");
        vote.setVotingSessionId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setVoteValue(VoteValue.YES);

        votingSession.setId("62bd2ae7d435a23d8127f3c1");
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));

        try {
            voteService.voteAgenda(vote);
            fail("Expected exception");
        } catch (ResourceNotFoundException exception) {
            assertThat(exception.getMessage()).isEqualTo("Voting session is not active");
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    @DisplayName("should throw an exception when Agenda ID is empty")
    public void givenEmptyAgendaId_whenSaveVote_thenThrowException() {
        vote.setVoterId("123456789");
        vote.setVotingSessionId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setAgendaId(null);
        vote.setVoteValue(VoteValue.YES);

        votingSession.setId("62bd2ae7d435a23d8127f3c1");
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));

        try {
            voteService.voteAgenda(vote);
            fail("Expected exception");
        } catch (InvalidRequestException exception) {
            assertThat(exception.getMessage()).isEqualTo("Agenda ID, Voter ID and Vote Value are required");
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    @DisplayName("should throw an exception when Voter ID is empty")
    public void givenEmptyVoterId_whenSaveVote_thenThrowException() {
        vote.setVoterId(null);
        vote.setVotingSessionId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setVoteValue(VoteValue.YES);

        votingSession.setId("62bd2ae7d435a23d8127f3c1");
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));

        try {
            voteService.voteAgenda(vote);
            fail("Expected exception");
        } catch (InvalidRequestException exception) {
            assertThat(exception.getMessage()).isEqualTo("Agenda ID, Voter ID and Vote Value are required");
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    @DisplayName("should throw an exception when Vote Value is empty")
    public void givenEmptyVoteValue_whenSaveVote_thenThrowException() {
        vote.setVoterId("123456789");
        vote.setVotingSessionId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setVoteValue(null);

        votingSession.setId("62bd2ae7d435a23d8127f3c1");
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));

        try {
            voteService.voteAgenda(vote);
            fail("Expected exception");
        } catch (InvalidRequestException exception) {
            assertThat(exception.getMessage()).isEqualTo("Agenda ID, Voter ID and Vote Value are required");
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    @DisplayName("should throw an exception when voter has already voted")
    public void givenVoterHasAlreadyVoted_whenSaveVote_thenThrowException() {
        when(voteRepository.findByVoterIdAndAgendaId(Mockito.any(), Mockito.any())).thenReturn(Optional.of(vote));
        when(voterValidationService.isValidVoter(vote.getVoterId())).thenReturn(true);

        vote.setVoterId("123456789");
        vote.setVotingSessionId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));
        vote.setVoteValue(VoteValue.YES);

        votingSession.setId("62bd2ae7d435a23d8127f3c1");
        votingSession.setSessionDurationInSeconds(60);
        votingSession.setAgendaId(new ObjectId("62bd2ae7d435a23d8127f3c1"));

        try {
            voteService.voteAgenda(vote);
            fail("Expected exception");
        } catch (InvalidRequestException exception) {
            assertThat(exception.getMessage()).isEqualTo("Voter has already voted for this agenda");
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
