package com.votingsystem.VotingSystem.entities;

import com.votingsystem.VotingSystem.enums.VoteValue;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "votes")
public class Vote {
    @Id
    private String id;

    private String voterId;

    private VoteValue voteValue;

    private ObjectId agendaId;

    private ObjectId votingSessionId;

    private Date voteDate;

    public Vote() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public VoteValue getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(VoteValue voteValue) {
        this.voteValue = voteValue;
    }

    public ObjectId getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(ObjectId agendaId) {
        this.agendaId = agendaId;
    }

    public ObjectId getVotingSessionId() {
        return votingSessionId;
    }

    public void setVotingSessionId(ObjectId votingSessionId) {
        this.votingSessionId = votingSessionId;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }
}
