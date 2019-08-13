package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Filter {
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = 1;

    private String value;

    public Filter() {
    }

    public Filter(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
