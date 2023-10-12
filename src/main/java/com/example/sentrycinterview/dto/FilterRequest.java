package com.example.sentrycinterview.dto;

import java.util.List;

public class FilterRequest {

    private String sellerName;
    private List<String> producerIds;
    private List<String> marketplaceIds;
    private String sortDirection;
    private Integer page;
    private Integer size;

    // Constructors, getters, and setters

    public FilterRequest(String sellerName, List<String> producerIds, List<String> marketplaceIds,
        String sortDirection, Integer page, Integer size) {
        this.sellerName = sellerName;
        this.producerIds = producerIds;
        this.marketplaceIds = marketplaceIds;
        this.page = page;
        this.size = size;
        this.sortDirection = sortDirection;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<String> getProducerIds() {
        return producerIds;
    }

    public void setProducerIds(List<String> producerIds) {
        this.producerIds = producerIds;
    }

    public List<String> getMarketplaceIds() {
        return marketplaceIds;
    }

    public void setMarketplaceIds(List<String> marketplaceIds) {
        this.marketplaceIds = marketplaceIds;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
