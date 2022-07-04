package com.votingsystem.VotingSystem.repositories;

import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionRepository;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface VotingSessionMongoRepository extends IVotingSessionRepository, MongoRepository<VotingSession, String> { }
