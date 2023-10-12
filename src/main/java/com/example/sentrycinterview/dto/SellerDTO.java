package com.example.sentrycinterview.dto;

import java.util.List;

public class SellerDTO {

    private String sellerName;
    private String externalId;
    private String marketplaceId;
    private List<ProducerSellerStateDTO> producerSellerStates;

    // Constructors, Getters, and Setters
    public SellerDTO(String sellerName, String externalId, String marketplaceId) {
        this.sellerName = sellerName;
        this.externalId = externalId;
        this.marketplaceId = marketplaceId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<ProducerSellerStateDTO> getProducerSellerStates() {
        return producerSellerStates;
    }

    public void setProducerSellerStates(
        List<ProducerSellerStateDTO> producerSellerStates) {
        this.producerSellerStates = producerSellerStates;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }
}
