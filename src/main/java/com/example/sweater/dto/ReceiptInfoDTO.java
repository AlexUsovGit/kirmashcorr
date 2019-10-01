package com.example.sweater.dto;


public class ReceiptInfoDTO {
    private String saleDate;
    private String storeName;
    private String saleCost;

    public ReceiptInfoDTO() {
    }

    public ReceiptInfoDTO(String saleDate, String storeName, String saleCost) {
        this.saleDate = saleDate;
        this.storeName = storeName;
        this.saleCost = saleCost;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
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
