package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.VotingSession;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface IVotingSessionRepository extends CrudRepository<VotingSession, String> {
    public Optional<VotingSession> findByAgendaIdEqualsAndStartGreaterThanEqualAndEndLessThanEqual(String agendaId, Date start, Date end);
}
