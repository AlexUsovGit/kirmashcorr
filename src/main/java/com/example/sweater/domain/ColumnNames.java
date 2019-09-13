package com.example.sweater.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
//@Immutable
//@Subselect(
//        "SELECT column_name FROM information_schema.tables where table_schema = 'public'")
public class ColumnNames implements Serializable {
    @Id
    private String column_name;

    public ColumnNames() {
    }

    public ColumnNames(String column_name) {
        this.column_name = column_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }
}
