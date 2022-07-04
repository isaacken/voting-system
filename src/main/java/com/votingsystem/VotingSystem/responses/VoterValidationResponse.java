package com.votingsystem.VotingSystem.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class VoterValidationResponse {
    @JsonProperty
    public String status;
}
