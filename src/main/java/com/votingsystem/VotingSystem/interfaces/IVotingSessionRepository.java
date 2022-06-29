package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.VotingSession;
import org.springframework.data.repository.CrudRepository;

public interface IVotingSessionRepository extends CrudRepository<VotingSession, String> {
}
