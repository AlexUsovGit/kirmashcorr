package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Receipt implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String productName;
    private String receiptNumber;
    private String barcode;
    private String retailPrice;
    private Date saleDate;
    private String count;
    private String discount;
    private String cost;
    private String status;
    private String gender;
    private String storeName;
    private String author;
    private String dateArrive;
    private String boxNumber;




    public Receipt() {
    }

    public Receipt(String productName, String receiptNumber ,String barcode, String retailPrice, Date saleDate, String count, String discount,
                   String cost, String status, String gender, String storeName, String author,
                    String dateArrive,String boxNumber) {
        this.productName = productName;
        this.receiptNumber = receiptNumber;
        this.barcode = barcode;
        this.retailPrice = retailPrice;
        this.saleDate = saleDate;
        this.count = count;
        this.discount = discount;
        this.cost = cost;
        this.status = status;
        this.gender = gender;
        this.storeName = storeName;
        this.author = author;
        this.dateArrive = dateArrive;
        this.boxNumber = boxNumber;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }
}
