package com.example.sweater.domain;


import jdk.nashorn.internal.ir.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Immutable
@Subselect(
        "SELECT table_name FROM information_schema.tables where table_schema = 'public'")
public class TableNames implements Serializable {
    @Id
    private String table_name;

    public TableNames() {
    }

    public TableNames(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }
}
