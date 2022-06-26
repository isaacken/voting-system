package com.votingsystem.VotingSystem.repositories;

import com.votingsystem.VotingSystem.entities.Agenda;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendaRepository extends MongoRepository<Agenda, String> {
}
