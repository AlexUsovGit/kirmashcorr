package com.example.sweater.domain.basedictionary;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProductName {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String label;
    private String author;
    private String isActive;
    @Column(name = "lastUpdate")
    private Date lastUpdate;

    public ProductName() {
    }

    public ProductName(String label, String author, String isActive) {
        this.label = label;
        this.author = author;
        this.isActive = isActive;
    }

    public ProductName(String label, String author, String isActive, Date lastUpdate) {
        this.label = label;
        this.author = author;
        this.isActive = isActive;
        this.lastUpdate = lastUpdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}