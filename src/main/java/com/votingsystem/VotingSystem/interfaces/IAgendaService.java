package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.Agenda;

import java.util.Optional;

public interface IAgendaService {
    Agenda create(Agenda agenda) throws Exception;
    Optional<Agenda> findById(String agenda);
}

