package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.Vote;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IVoteRepository extends CrudRepository<Vote, String> {
    public Optional<Vote> findByVoterIdAndAgendaId(String voterId, ObjectId agendaId);
    public List<Vote> findAllByAgendaId(ObjectId agendaId);
}
