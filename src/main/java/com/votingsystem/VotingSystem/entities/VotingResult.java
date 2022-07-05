package com.votingsystem.VotingSystem.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "voting_results")
public class VotingResult {
    @Id
    private String id;

    private ObjectId agendaId;

    private int yesVotes;

    private int noVotes;

    public VotingResult() {
    }

    public ObjectId getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(ObjectId agendaId) {
        this.agendaId = agendaId;
    }

    public int getYesVotes() {
        return yesVotes;
    }

    public void setYesVotes(int yesVotes) {
        this.yesVotes = yesVotes;
    }

    public int getNoVotes() {
        return noVotes;
    }

    public void setNoVotes(int noVotes) {
        this.noVotes = noVotes;
    }
}
