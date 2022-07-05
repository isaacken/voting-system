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
        if (!isVotingSessionValid(votingSession)) {
            throw new Exception("Invalid voting session");
        }

        if (agendaService.findById(votingSession.getAgendaId().toString()).isEmpty()) {
            throw new Exception("Agenda not found");
        }

        votingSession.setStart(new Date());
        var startTimestamp = votingSession.getStart().getTime();
        var end = new Date(startTimestamp + votingSession.getSessionDurationInSeconds() * 1000L);
        votingSession.setEnd(end);

        var createdVotingSession = votingSessionRepository.save(votingSession);

        dispatchVotingSessionTerminator(votingSession.getSessionDurationInSeconds());

        return createdVotingSession;
    }

    private void dispatchVotingSessionTerminator(int timeSeconds) {
        new Thread(() -> {
            try {
                Thread.sleep(timeSeconds * 1000L);
                endVotingSession();
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        }).start();
    }

    public void endVotingSession() {

    }

    public boolean isVotingSessionValid(VotingSession votingSession) {
        if (votingSession.getAgendaId() == null) {
            return false;
        }

        return true;
    }
}
