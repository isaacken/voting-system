package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.VotingResult;
import org.springframework.data.repository.CrudRepository;

public interface IVotingResultRepository extends CrudRepository<VotingResult, String> { }
