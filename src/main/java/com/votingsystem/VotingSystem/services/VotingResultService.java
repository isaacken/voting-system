package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.Vote;
import com.votingsystem.VotingSystem.entities.VotingResult;
import com.votingsystem.VotingSystem.enums.VoteValue;
import com.votingsystem.VotingSystem.interfaces.IAgendaRepository;
import com.votingsystem.VotingSystem.interfaces.IVoteRepository;
import com.votingsystem.VotingSystem.interfaces.IVotingResultRepository;
import com.votingsystem.VotingSystem.interfaces.IVotingResultService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingResultService implements IVotingResultService {
    private final IVotingResultRepository votingResultRepository;
    private final IVoteRepository voteRepository;

    @Autowired
    public VotingResultService(IVotingResultRepository votingResultRepository, IVoteRepository voteRepository) {
        this.votingResultRepository = votingResultRepository;
        this.voteRepository = voteRepository;
    }

    public VotingResult getVotingResult(ObjectId agendaId) throws Exception {
        var votes = voteRepository.findAllByAgendaId(agendaId);
        int yesVotes = 0, noVotes = 0;
        for (var vote : votes) {
            if (vote.getVoteValue() == VoteValue.YES) {
                yesVotes++;
            } else {
                noVotes++;
            }
        }

        var votingResult = new VotingResult();
        votingResult.setAgendaId(agendaId);
        votingResult.setYesVotes(yesVotes);
        votingResult.setNoVotes(noVotes);

        votingResultRepository.save(votingResult);

        return votingResult;
    }
}
