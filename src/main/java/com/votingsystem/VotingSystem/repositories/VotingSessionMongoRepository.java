package com.votingsystem.VotingSystem.repositories;

import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSessionMongoRepository extends IVotingSessionRepository, MongoRepository<VotingSession, String> {
}
