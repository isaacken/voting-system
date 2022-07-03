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
    private String id;

    private Date start;

    private Date end;
    private int sessionDurationInSeconds;

    private ObjectId agendaId;

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

    public ObjectId getAgendaId() {
        return agendaId;
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

    public void setId(String id) {
        this.id = id;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
