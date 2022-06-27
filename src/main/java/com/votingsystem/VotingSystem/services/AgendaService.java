package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.Agenda;
import com.votingsystem.VotingSystem.interfaces.IAgendaRepository;
import com.votingsystem.VotingSystem.interfaces.IAgendaService;
import com.votingsystem.VotingSystem.repositories.AgendaMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService implements IAgendaService {
    private final IAgendaRepository agendaMongoRepository;

    @Autowired
    public AgendaService(IAgendaRepository agendaMongoRepository) {
        this.agendaMongoRepository = agendaMongoRepository;
    }

    public Agenda create(Agenda agenda) throws Exception {
        validateAgenda(agenda);
        return agendaMongoRepository.save(agenda);
    }

    private void validateAgenda(Agenda agenda) throws Exception {
        if (agenda.getQuestion().isBlank())
            throw new Exception("Cannot create new agenda to vote without a question");
    }
}
