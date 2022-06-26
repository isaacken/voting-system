package com.votingsystem.VotingSystem.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "agendas")
public class Agenda {
    @Id
    private String id;

    private String question;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastModifiedDate;

    public Agenda() { }

    public Agenda(String id, String question, Date createdAt, Date lastModifiedDate) {
        this.id = id;
        this.question = question;
        this.createdAt = createdAt;
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
}
