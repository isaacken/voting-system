package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.Vote;
import com.votingsystem.VotingSystem.exceptions.InvalidRequestException;
import com.votingsystem.VotingSystem.exceptions.ResourceNotFoundException;

public interface IVoteService {
    Vote voteAgenda(Vote vote) throws InvalidRequestException, ResourceNotFoundException;
}
