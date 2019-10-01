package com.example.sweater.domain;

public class InfoDTO {
    private String productName;
    private Integer productCount;

    public InfoDTO() {
    }

    public InfoDTO(String productName, Integer productCount) {
        this.productName = productName;
        this.productCount = productCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
