package com.votingsystem.VotingSystem.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "voting_sessions")
public class VotingSession {
    public static final int DEFAULT_SESSION_DURATION = 60;

    @Id
    public String id;

    public Date start;

    public Date end;
    public int sessionDurationInSeconds;

    public ObjectId agendaId;

    public VotingSession() {
        sessionDurationInSeconds = DEFAULT_SESSION_DURATION;
    }

    public String getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getSessionDurationInSeconds() {
        return sessionDurationInSeconds;
    }

    public void setSessionDurationInSeconds(int sessionDurationInSeconds) {
        this.sessionDurationInSeconds = sessionDurationInSeconds;
    }

    public void setAgendaId(ObjectId agendaId) {
        this.agendaId = agendaId;
    }
}
