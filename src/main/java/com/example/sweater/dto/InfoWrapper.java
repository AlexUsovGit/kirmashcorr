package com.example.sweater.dto;

import java.util.List;

public class InfoWrapper {
    private List<Double> saleCost;
    private List<String> saleDate;
    private String storeName;

    public InfoWrapper() {
    }

    public InfoWrapper(List<Double> saleCost, List<String> saleDate, String storeName) {
        this.saleCost = saleCost;
        this.saleDate = saleDate;
        this.storeName = storeName;
    }

    public List<Double> getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(List<Double> saleCost) {
        this.saleCost = saleCost;
    }

    public List<String> getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(List<String> saleDate) {
        this.saleDate = saleDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}