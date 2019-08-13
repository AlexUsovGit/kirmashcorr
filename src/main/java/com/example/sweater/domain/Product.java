package com.example.sweater.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.Set;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String productName;
    private String gender;
    private String size;
    private String trademark;
    private String importer;
    private String manufacturer;
    private String article;
    private String code;
    private String composition;
    private String season;
    private String barcode;
    private String note;
    private String quantity;
    private String dateArrive;
    private String importPrice;
    private String coefficient ;
    private String retailPrice;
    private String countryOfEntry ;
    private String currency;
    private String course;
    private Integer isDistrib;
    private String boxNumber;



    public Product() {
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public Product(String productName, String gender, String size, String trademark, String importer,
                   String manufacturer, String article, String code, String composition, String season, String barcode,
                   String note, String quantity, String dateArrive, String importPrice, String coefficient,
                   String retailPrice, String countryOfEntry, String currency, String course, Integer isDistrib, String boxNumber) {
        this.productName = productName;
        this.gender = gender;
        this.size = size;
        this.trademark = trademark;
        this.importer = importer;
        this.manufacturer = manufacturer;
        this.article = article;
        this.code = code;
        this.composition = composition;
        this.season = season;
        this.barcode = barcode;
        this.note = note;
        this.quantity = quantity;
        this.dateArrive = dateArrive;
        this.importPrice = importPrice;
        this.coefficient = coefficient;
        this.retailPrice = retailPrice;
        this.countryOfEntry = countryOfEntry;
        this.currency = currency;
        this.course = course;
        this.isDistrib = isDistrib;
        this.boxNumber = boxNumber;
    }

    public String getStringDate(){
        Date date1;
        String dateStr;
        try {
            date1 =new SimpleDateFormat("yyyy-MM-dd").parse(dateArrive);
            dateStr =  theMonth(Integer.parseInt(new SimpleDateFormat("M").format(date1))-1) +
            " " + new SimpleDateFormat("yyyy").format(date1)
            ;
        } catch (ParseException e) {
            e.printStackTrace();
            dateStr = "-";
        }

        return dateStr;
    }
    public static String theMonth(int month){
        String[] monthNames = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август",
                "сентябрь", "октябрь", "ноябрь", "декабрь"};
        return monthNames[month];
    }
    public double getDoubleQuantity() {
        if(quantity == null || quantity.isEmpty()){
            return 0;
        }
        return Double.parseDouble(quantity);
    }
    public double getDoubleImportPrice() {
        if(importPrice == null || importPrice.isEmpty()){
            return 0;
        }
        return Double.parseDouble(importPrice);
    }
    public double getDoubleRetailPrice() {
        if(retailPrice == null || retailPrice.isEmpty()){
            return 0;
        }
        return Double.parseDouble(retailPrice);
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getImporter() {
        return importer;
    }

    public void setImporter(String importer) {
        this.importer = importer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDateArrive() {


        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }

    public String getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(String importPrice) {
        this.importPrice = importPrice;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getCountryOfEntry() {
        return countryOfEntry;
    }

    public void setCountryOfEntry(String countryOfEntry) {
        this.countryOfEntry = countryOfEntry;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getIsDistrib() {
        return isDistrib;
    }

    public void setIsDistrib(Integer isDistrib) {
        this.isDistrib = isDistrib;
    }



}
