package com.votingsystem.VotingSystem.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class VoteAgendaRequest {
    @JsonProperty("voter_id")
    private String voterId;

    @JsonProperty("agenda_id")
    private String agendaId;

    @JsonProperty("vote_value")
    private String voteValue;

    public String getVoterId() {
        return voterId;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public String getVoteValue() {
        return voteValue;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public void setVoteValue(String voteValue) {
        this.voteValue = voteValue;
    }
}
