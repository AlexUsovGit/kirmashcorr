package com.example.sweater.dto;

import java.util.List;

public class InfoWrapper {
    private List<Double> saleCost;
    private List<String> saleDate;

    public InfoWrapper() {
    }

    public InfoWrapper(List<Double> saleCost, List<String> saleDate) {
        this.saleCost = saleCost;
        this.saleDate = saleDate;
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
}