package com.example.sweater.domain;


import jdk.nashorn.internal.ir.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
//@Immutable
//@Subselect(
//        "SELECT table_name, column_name FROM information_schema.columns where table_schema = 'public' order by table_name ASC")
public class ColumnNames implements Serializable {
    @Id
    private String table_name;
    private String column_name;

    public ColumnNames() {
    }

    public ColumnNames(String table_name, String column_name) {
        this.table_name = table_name;
        this.column_name = column_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }
}
