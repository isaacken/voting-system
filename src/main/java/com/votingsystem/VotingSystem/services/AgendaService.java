package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.Agenda;
import com.votingsystem.VotingSystem.interfaces.IAgendaRepository;
import com.votingsystem.VotingSystem.interfaces.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendaService implements IAgendaService {
    private final IAgendaRepository agendaRepository;

    @Autowired
    public AgendaService(IAgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda create(Agenda agenda) throws Exception {
        validateAgenda(agenda);
        return agendaRepository.save(agenda);
    }

    public Optional<Agenda> findById(String id) {
        return agendaRepository.findById(id);
    }

    private void validateAgenda(Agenda agenda) throws Exception {
        if (agenda.getQuestion().isBlank())
            throw new Exception("Cannot create new agenda to vote without a question");
    }
}
