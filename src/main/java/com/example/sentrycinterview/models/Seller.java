package com.example.sentrycinterview.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

    @ManyToOne
    @JoinColumn(name = "seller_info_id")
    private SellerInfo sellerInfo;

    @Column(name = "state", nullable = false, length = 255)
    private String state;

    // Constructors, getters, and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public SellerInfo getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

