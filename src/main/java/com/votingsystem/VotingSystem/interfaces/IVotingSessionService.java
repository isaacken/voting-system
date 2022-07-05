package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.exceptions.InvalidRequestException;

public interface IVotingSessionService {
    public VotingSession startSession(VotingSession votingSession) throws InvalidRequestException;
}
