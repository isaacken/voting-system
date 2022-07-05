package com.votingsystem.VotingSystem.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class StartSessionRequest {
    @JsonProperty("agenda_id")
    private String agendaId;

    @JsonProperty("session_duration")
    private int sessionDurationInSeconds;

    public String getAgendaId() {
        return agendaId;
    }

    public int getSessionDurationInSeconds() {
        return sessionDurationInSeconds;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public void setSessionDurationInSeconds(int sessionDurationInSeconds) {
        this.sessionDurationInSeconds = sessionDurationInSeconds;
    }
}
