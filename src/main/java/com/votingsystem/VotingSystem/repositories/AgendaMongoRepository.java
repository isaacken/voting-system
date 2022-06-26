package com.votingsystem.VotingSystem.repositories;

import com.votingsystem.VotingSystem.entities.Agenda;
import com.votingsystem.VotingSystem.interfaces.IAgendaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaMongoRepository extends IAgendaRepository, MongoRepository<Agenda, String> {
}
