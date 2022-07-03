package com.votingsystem.VotingSystem.repositories;

import com.votingsystem.VotingSystem.entities.Vote;
import com.votingsystem.VotingSystem.interfaces.IVoteRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface VoteMongoRepository extends IVoteRepository, MongoRepository<Vote, String> { }
