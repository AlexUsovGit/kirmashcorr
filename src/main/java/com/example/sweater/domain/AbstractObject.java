package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AbstractObject extends Object {
    @Id
    private String info;

    public AbstractObject() {
    }

    public AbstractObject(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
