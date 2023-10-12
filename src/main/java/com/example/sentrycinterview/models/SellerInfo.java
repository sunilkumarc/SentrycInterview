package com.example.sentrycinterview.models;

import java.util.Objects;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "seller_infos")
public class SellerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "marketplace_id", nullable = false)
    private Marketplace marketplace;

    @Column(name = "name", nullable = false, length = 2048)
    private String name;

    @Column(name = "url", length = 2048)
    private String url;

    @Column(name = "country", length = 255)
    private String country;

    @Column(name = "external_id", length = 255)
    private String externalId;

    // Constructors, getters, and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SellerInfo that = (SellerInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(marketplace,
            that.marketplace) && Objects.equals(name, that.name) && Objects.equals(
            url, that.url) && Objects.equals(country, that.country)
            && Objects.equals(externalId, that.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marketplace, name, url, country, externalId);
    }
}
