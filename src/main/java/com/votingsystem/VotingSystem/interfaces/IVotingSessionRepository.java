package com.votingsystem.VotingSystem.interfaces;

import com.votingsystem.VotingSystem.entities.VotingSession;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface IVotingSessionRepository extends CrudRepository<VotingSession, String> {
    @Query("{'agendaId':  ?0, 'start': { $lte: {$date: ?1} }, 'end': {$gte: {$date: ?1}}}")
    public Optional<VotingSession> findActiveVotingSessionForAgenda(ObjectId agendaId, Date now);
}
