package com.example.sentrycinterview.dto;

import java.util.List;

public class SellerResponseDTO {

    private MetaDTO meta;
    private List<SellerDTO> data;

    // Constructors, Getters, and Setters

    public SellerResponseDTO(MetaDTO meta, List<SellerDTO> data) {
        this.meta = meta;
        this.data = data;
    }

    public void setData(List<SellerDTO> data) {
        this.data = data;
    }

    public MetaDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaDTO meta) {
        this.meta = meta;
    }

    public List<SellerDTO> getData() {
        return data;
    }
}