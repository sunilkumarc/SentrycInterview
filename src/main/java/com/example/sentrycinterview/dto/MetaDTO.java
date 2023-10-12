package com.example.sentrycinterview.dto;

public class MetaDTO {

    private int page;
    private int size;
    private int total;

    // Constructors, Getters, and Setters

    public MetaDTO(int page, int size, int total) {
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}