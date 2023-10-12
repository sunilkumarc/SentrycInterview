package com.example.sentrycinterview.models;

import javax.persistence.*;

@Entity
@Table(name = "marketplaces")
public class Marketplace {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "description")
    private String description;

    // Constructors, getters, and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}