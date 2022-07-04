package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.Vote;
import com.votingsystem.VotingSystem.exceptions.InvalidRequestException;
import com.votingsystem.VotingSystem.exceptions.ResourceNotFoundException;
import com.votingsystem.VotingSystem.interfaces.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VoteService implements IVoteService {
    private final IVoteRepository voteRepository;
    private final IVotingSessionRepository votingSessionRepository;

    private final IVoterValidationService voterValidationService;

    @Autowired
    public VoteService(IVoteRepository voteRepository, IVotingSessionRepository votingSessionRepository, IVoterValidationService voterValidationService) {
        this.voteRepository = voteRepository;
        this.voterValidationService = voterValidationService;
        this.votingSessionRepository = votingSessionRepository;
    }

    public Vote voteAgenda(Vote vote) throws InvalidRequestException, ResourceNotFoundException {
        if (!isVoteValid(vote)) {
            throw new InvalidRequestException("Agenda ID, Voter ID and Vote Value are required");
        }

        if (!voterValidationService.isValidVoter(vote.getVoterId())) {
            throw new ResourceNotFoundException("Voter is not valid");
        }

        if (!voteRepository.findByVoterIdAndAgendaId(vote.getVoterId(), vote.getAgendaId()).isEmpty()) {
            throw new InvalidRequestException("Voter has already voted for this agenda");
        }

        var now = new Date();
        var votingSession = votingSessionRepository.findActiveVotingSessionForAgenda(vote.getAgendaId(), now);
        if (votingSession.isEmpty()) {
            throw new ResourceNotFoundException("Voting session is not active");
        }

        vote.setVotingSessionId(new ObjectId(votingSession.get().getId()));

        return voteRepository.save(vote);
    }

    private boolean isVoteValid(Vote vote) {
        if (vote.getAgendaId() == null || vote.getVoteValue() == null || vote.getVoterId() == null) {
            return false;
        }

        return true;
    }
}
