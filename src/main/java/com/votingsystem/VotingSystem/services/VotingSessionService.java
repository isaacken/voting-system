package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.exceptions.InvalidRequestException;
import com.votingsystem.VotingSystem.interfaces.IAgendaService;
import com.votingsystem.VotingSystem.interfaces.IVotingResultService;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionRepository;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VotingSessionService implements IVotingSessionService {
    private final IAgendaService agendaService;

    private final IVotingSessionRepository votingSessionRepository;

    private final IVotingResultService votingResultService;

    @Autowired
    public VotingSessionService(IAgendaService agendaService, IVotingSessionRepository votingSessionRepository, IVotingResultService votingResultService) {
        this.agendaService = agendaService;
        this.votingSessionRepository = votingSessionRepository;
        this.votingResultService = votingResultService;
    }

    public VotingSession startSession(VotingSession votingSession) throws InvalidRequestException {
        if (!isVotingSessionValid(votingSession)) {
            throw new InvalidRequestException("Invalid voting session");
        }

        if (agendaService.findById(votingSession.getAgendaId().toString()).isEmpty()) {
            throw new InvalidRequestException("Agenda not found");
        }

        votingSession.setStart(new Date());
        var startTimestamp = votingSession.getStart().getTime();
        var end = new Date(startTimestamp + votingSession.getSessionDurationInSeconds() * 1000L);
        votingSession.setEnd(end);

        var createdVotingSession = votingSessionRepository.save(votingSession);

        dispatchVotingSessionTerminator(votingSession.getSessionDurationInSeconds(), createdVotingSession);

        return createdVotingSession;
    }

    private void dispatchVotingSessionTerminator(int timeSeconds, VotingSession votingSession) {
        new Thread(() -> {
            try {
                Thread.sleep(timeSeconds * 1000L);
                votingResultService.getVotingResult(votingSession.getAgendaId());
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        }).start();
    }

    public boolean isVotingSessionValid(VotingSession votingSession) {
        if (votingSession.getAgendaId() == null) {
            return false;
        }

        return true;
    }
}
