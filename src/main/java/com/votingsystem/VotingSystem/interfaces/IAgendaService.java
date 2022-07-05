package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.Agenda;
import com.votingsystem.VotingSystem.exceptions.InvalidRequestException;

import java.util.Optional;

public interface IAgendaService {
    Agenda create(Agenda agenda) throws InvalidRequestException;
    Optional<Agenda> findById(String agenda);
}

