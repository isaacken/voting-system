package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.Vote;

public interface IVoteService {
    Vote voteAgenda(Vote vote) throws Exception;
}
