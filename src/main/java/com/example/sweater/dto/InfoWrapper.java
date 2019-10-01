package com.example.sweater.dto;

import java.util.List;

public class InfoWrapper {
    private List<Double> dataSale;
    private List<String> dateSale;

    public InfoWrapper() {
    }

    public InfoWrapper(List<Double> dataSale, List<String> dateSale) {
        this.dataSale = dataSale;
        this.dateSale = dateSale;
    }

    public List<Double> getDataSale() {
        return dataSale;
    }

    public void setDataSale(List<Double> dataSale) {
        this.dataSale = dataSale;
    }

    public List<String> getDateSale() {
        return dateSale;
    }

    public void setDateSale(List<String> dateSale) {
        this.dateSale = dateSale;
    }
}
