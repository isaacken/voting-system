package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IVoteRepository extends CrudRepository<Vote, String> {
    public Optional<Vote> findByVoterIdAndAgendaId(String voterId, String agendaId);
}
