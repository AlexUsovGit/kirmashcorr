package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class ReportShopReceiptFilter {
    @Id
    private long id = 1;
    private String department;
    private String dateFrom;
    private String dateTo;

    public ReportShopReceiptFilter() {
    }

    public ReportShopReceiptFilter(String department, String dateFrom, String dateTo) {
        this.department = department;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
