package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.Vote;
import com.votingsystem.VotingSystem.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public Vote voteAgenda(Vote vote) throws Exception {
        if (!isVoteValid(vote)) {
            throw new Exception("Agenda ID, Voter ID and Vote Value are required");
        }

        if (!voterValidationService.isValidVoter(vote.getVoterId())) {
            throw new Exception("Voter is not valid");
        }

        if (!voteRepository.findByVoterIdAndAgendaId(vote.getVoterId(), vote.getAgendaId().toString()).isEmpty()) {
            throw new Exception("Voter has already voted for this agenda");
        }

        var now = new Date();
        if (votingSessionRepository.findByAgendaIdEqualsAndStartGreaterThanEqualAndEndLessThanEqual(vote.getAgendaId().toString(), now, now).isEmpty()) {
            throw new Exception("Voting session is not active");
        }

        return voteRepository.save(vote);
    }

    private boolean isVoteValid(Vote vote) {
        if (vote.getAgendaId() == null || vote.getVoteValue() == null || vote.getVoterId() == null) {
            return false;
        }

        return true;
    }
}
