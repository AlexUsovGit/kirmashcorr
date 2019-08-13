package com.example.sweater.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class InfoClass implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long newid;

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
    private long id_distrDoc;
    private Integer docNumber;
    private Date dateDistrib;
    private String username;
    private Integer countSklad;
   private Integer countM1;
    private Integer countM2;
    private Integer countM3;
    private Integer countM4;
    private Integer countM5;
    private Integer countM6;
    private Integer countM7;
    private Integer countM8;
    private Long product_id;
    private String dateDistribTxt;


    public InfoClass() {
    }

    public InfoClass(long id, String productName, String gender, String size, String trademark, String importer, String manufacturer, String article, String code, String composition, String season, String barcode, String note, String quantity, String dateArrive, String importPrice, String coefficient, String retailPrice, String countryOfEntry, String currency, String course, Integer isDistrib, long id_distrDoc, Integer docNumber, Date dateDistrib, String username, Integer countSklad, Integer countM1, Integer countM2, Integer countM3, Integer countM4, Integer countM5, Integer countM6, Integer countM7, Integer countM8, Long product_id, String dateDistribTxt) {
        this.id = id;
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
        this.id_distrDoc = id_distrDoc;
        this.docNumber = docNumber;
        this.dateDistrib = dateDistrib;
        this.username = username;
        this.countSklad = countSklad;
        this.countM1 = countM1;
        this.countM2 = countM2;
        this.countM3 = countM3;
        this.countM4 = countM4;
        this.countM5 = countM5;
        this.countM6 = countM6;
        this.countM7 = countM7;
        this.countM8 = countM8;
        this.product_id = product_id;
        this.dateDistribTxt = dateDistribTxt;
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

    public long getId_distrDoc() {
        return id_distrDoc;
    }

    public void setId_distrDoc(long id_distrDoc) {
        this.id_distrDoc = id_distrDoc;
    }

    public Integer getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Integer docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDateDistrib() {
        return dateDistrib;
    }

    public void setDateDistrib(Date dateDistrib) {
        this.dateDistrib = dateDistrib;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCountSklad() {
        return countSklad;
    }

    public void setCountSklad(Integer countSklad) {
        this.countSklad = countSklad;
    }

    public Integer getCountM1() {
        return countM1;
    }

    public void setCountM1(Integer countM1) {
        this.countM1 = countM1;
    }

    public Integer getCountM2() {
        return countM2;
    }

    public void setCountM2(Integer countM2) {
        this.countM2 = countM2;
    }

    public Integer getCountM3() {
        return countM3;
    }

    public void setCountM3(Integer countM3) {
        this.countM3 = countM3;
    }

    public Integer getCountM4() {
        return countM4;
    }

    public void setCountM4(Integer countM4) {
        this.countM4 = countM4;
    }

    public Integer getCountM5() {
        return countM5;
    }

    public void setCountM5(Integer countM5) {
        this.countM5 = countM5;
    }

    public Integer getCountM6() {
        return countM6;
    }

    public void setCountM6(Integer countM6) {
        this.countM6 = countM6;
    }

    public Integer getCountM7() {
        return countM7;
    }

    public void setCountM7(Integer countM7) {
        this.countM7 = countM7;
    }

    public Integer getCountM8() {
        return countM8;
    }

    public void setCountM8(Integer countM8) {
        this.countM8 = countM8;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getDateDistribTxt() {
        return dateDistribTxt;
    }

    public void setDateDistribTxt(String dateDistribTxt) {
        this.dateDistribTxt = dateDistribTxt;
    }
}
