package com.votingsystem.VotingSystem.repositories;

import com.votingsystem.VotingSystem.entities.VotingResult;
import com.votingsystem.VotingSystem.interfaces.IVotingResultRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface VotingResultMongoRepository extends IVotingResultRepository, MongoRepository<VotingResult, String> { }
