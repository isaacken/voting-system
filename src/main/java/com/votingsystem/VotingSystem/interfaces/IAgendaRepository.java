package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.Agenda;
import org.springframework.data.repository.CrudRepository;

public interface IAgendaRepository extends CrudRepository<Agenda, String> {
}
