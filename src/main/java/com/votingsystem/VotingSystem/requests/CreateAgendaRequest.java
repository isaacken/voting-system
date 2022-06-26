package com.votingsystem.VotingSystem.requests;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CreateAgendaRequest {
    private String question;

    public  CreateAgendaRequest() {}

    public CreateAgendaRequest(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
