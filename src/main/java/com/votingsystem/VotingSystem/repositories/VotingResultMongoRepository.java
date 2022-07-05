package com.votingsystem.VotingSystem.repositories;

import com.votingsystem.VotingSystem.entities.VotingResult;
import com.votingsystem.VotingSystem.interfaces.IVotingResultRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotingResultMongoRepository extends IVotingResultRepository, MongoRepository<VotingResult, String> { }
