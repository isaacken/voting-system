package com.votingsystem.VotingSystem.services;

import com.votingsystem.VotingSystem.entities.VotingSession;
import com.votingsystem.VotingSystem.interfaces.IAgendaService;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionRepository;
import com.votingsystem.VotingSystem.interfaces.IVotingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VotingSessionService implements IVotingSessionService {
    private final IAgendaService agendaService;

    private final IVotingSessionRepository votingSessionRepository;

    @Autowired
    public VotingSessionService(IAgendaService agendaService, IVotingSessionRepository votingSessionRepository) {
        this.agendaService = agendaService;
        this.votingSessionRepository = votingSessionRepository;
    }

    public VotingSession startSession(VotingSession votingSession) throws Exception {
        if (!agendaService.findById(votingSession.agendaId).isPresent()) {
            throw new Exception("Agenda not found");
        }

        votingSession.start = new Date();
        var startTimestamp = votingSession.start.getTime();
        votingSession.end = new Date(startTimestamp + votingSession.sessionDurationInSeconds * 1000);
        var createdVotingSession = votingSessionRepository.save(votingSession);

        dispatchVotingSessionTerminator(votingSession.sessionDurationInSeconds);

        return createdVotingSession;
    }

    private void dispatchVotingSessionTerminator(int timeSeconds) {
        new Thread(() -> {
            try {
                Thread.sleep(timeSeconds * 1000L);
                endVotingSession();
            } catch (Exception exception) {
                System.err.println(exception);
            }
        }).start();
    }

    public void endVotingSession() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet.");
    };
}
