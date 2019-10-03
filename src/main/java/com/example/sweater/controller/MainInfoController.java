package com.example.sweater.controller;


import com.example.sweater.dto.InfoWrapper;
import com.example.sweater.dto.ReceiptInfoDTO;
import com.example.sweater.dto.StoreNameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mainInfo")
public class MainInfoController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String DATE_FORMATTER = "dd.MM";

    @GetMapping("/receiptByDate")
    private List<ReceiptInfoDTO> getReceiptSumCost() {
        String query = "select cast(sale_date as date) as sale_date, store_name,  " +
                "sum(cast(receipt.cost as decimal)) as sale_cost from receipt\n" +
                "group by store_name, sale_date\n" +
                "order by sale_date";

        return this.jdbcTemplate.query(query, new ReceiptInfoDTOMapper());
    }


    @GetMapping("/receiptData")
    private /*List<Double>*/ List<InfoWrapper> getReceiptSumCostData(@RequestParam String store_name) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        ArrayList<InfoWrapper> infoWrappers = new ArrayList<>();
        String query = "select cast(sale_date as date) as sale_date, store_name,  " +
                "sum(cast(receipt.cost as decimal)) as sale_cost from receipt\n" +
                " where store_name = ?" +
                "group by store_name, cast(sale_date as date)\n" +
                "order by cast(sale_date as date)";

        List<ReceiptInfoDTO> receiptInfoDTOList = new ArrayList<>();
        receiptInfoDTOList.addAll(this.jdbcTemplate.query(query, new ReceiptInfoDTOMapper(), store_name));

        List<Double> dataCost = new ArrayList<>();
        List<String> dateSale = new ArrayList<>();
        for (ReceiptInfoDTO receiptInfoDTO : receiptInfoDTOList) {

            dataCost.add(Double.valueOf(receiptInfoDTO.getSaleCost()));
            dateSale.add(receiptInfoDTO.getSaleDate().format(formatter));
        }
        infoWrappers.add(new InfoWrapper(dataCost, dateSale));
        return infoWrappers;
    }

    private class ReceiptInfoDTOMapper implements RowMapper<ReceiptInfoDTO> {

        public ReceiptInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReceiptInfoDTO receiptInfoDTO = new ReceiptInfoDTO();
            receiptInfoDTO.setSaleDate(rs.getDate("sale_date").toLocalDate());
            receiptInfoDTO.setStoreName(rs.getString("store_name"));
            receiptInfoDTO.setSaleCost(rs.getString("sale_cost"));
            return receiptInfoDTO;
        }
    }


}
