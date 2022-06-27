package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.Agenda;

public interface IAgendaService {
    Agenda create(Agenda agenda) throws Exception;
}

