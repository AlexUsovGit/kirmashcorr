package com.example.sweater.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InfoDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String orderNumber;
    private String outletName;
    private String productName;
    private double cost;
    private double count;
    private String sex;
    private double costBYN;
    private String startDate;
    private  String storeName;
    private  double countOnStore;
    private  double currencyRate;

    public InfoDTO() {
    }

    public InfoDTO(String orderNumber, String outletName, String productName, double cost, double count, String sex, double costBYN, String startDate, String storeName, double countOnStore, double currencyRate) {
        this.orderNumber = orderNumber;
        this.outletName = outletName;
        this.productName = productName;
        this.cost = cost;
        this.count = count;
        this.sex = sex;
        this.costBYN = costBYN;
        this.startDate = startDate;
        this.storeName = storeName;
        this.countOnStore = countOnStore;
        this.currencyRate = currencyRate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getCostBYN() {
        return costBYN;
    }

    public void setCostBYN(double costBYN) {
        this.costBYN = costBYN;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getCountOnStore() {
        return countOnStore;
    }

    public void setCountOnStore(double countOnStore) {
        this.countOnStore = countOnStore;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }
}
