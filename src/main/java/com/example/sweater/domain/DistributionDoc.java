package com.example.sweater.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "distr_doc")
public class DistributionDoc  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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


    public DistributionDoc() {
    }

    public DistributionDoc(Integer docNumber, Date dateDistrib, String username, Integer countSklad,
                           Integer countM1, Integer countM2, Integer countM3, Integer countM4,
                           Integer countM5, Integer countM6, Integer countM7, Integer countM8,
                           Long product_id, String dateDistribTxt) {
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

    public String getDateDistribTxt() {
        return dateDistribTxt;
    }

    public void setDateDistribTxt(String dateDistribTxt) {
        this.dateDistribTxt = dateDistribTxt;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


}
