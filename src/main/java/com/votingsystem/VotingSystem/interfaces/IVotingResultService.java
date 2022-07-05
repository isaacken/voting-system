package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.VotingResult;
import org.bson.types.ObjectId;

public interface IVotingResultService {
    public VotingResult getVotingResult(ObjectId agendaId) throws Exception;
}
