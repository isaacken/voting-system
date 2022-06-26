package com.votingsystem.VotingSystem.responses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.votingsystem.VotingSystem.entities.Agenda;
import org.springframework.boot.jackson.JsonComponent;

import java.util.Date;

@JsonComponent
public class CreateAgendaResponse {
    private String id;

    private String question;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("modified_at")
    private Date modifiedAt;

    public CreateAgendaResponse() { }

    public CreateAgendaResponse(Agenda agenda) {
        this.id = agenda.getId();
        this.question = agenda.getQuestion();
        this.createdAt = agenda.getCreatedAt();
        this.modifiedAt = agenda.getLastModifiedDate();
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }
}

