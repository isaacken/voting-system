package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.VotingSession;

public interface IVotingSessionService {
    public VotingSession startSession(VotingSession votingSession) throws Exception;
}
