package com.example.sweater.dto;


import java.time.LocalDate;

public class ReceiptInfoDTO {
    private LocalDate saleDate;
    private String storeName;
    private String saleCost;

    public ReceiptInfoDTO() {
    }

    public ReceiptInfoDTO(LocalDate saleDate, String storeName, String saleCost) {
        this.saleDate = saleDate;
        this.storeName = storeName;
        this.saleCost = saleCost;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSaleCost() {
        return saleCost;
    }

    public void setSaleCost(String saleCost) {
        this.saleCost = saleCost;
    }
}