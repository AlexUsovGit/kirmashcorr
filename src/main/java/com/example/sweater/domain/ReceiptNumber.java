package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ReceiptNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String author;
    private Date date;
    private  String status;

    public ReceiptNumber() {
    }

    public ReceiptNumber(String author, Date date, String status) {
        this.author = author;
        this.date = date;
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
