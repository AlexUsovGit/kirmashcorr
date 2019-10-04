package com.example.sweater.controller;


import com.example.sweater.dto.InfoWrapper;
import com.example.sweater.dto.ReceiptInfoDTO;
import com.example.sweater.dto.StoreNameDTO;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/mainInfo")
public class MainInfoController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRepo userRepo;

    private static final String DATE_FORMATTER = "dd.MM";
    private static final String DATE_FORMATTER_DAY = "EEE";


    @GetMapping("/receiptByDate")
    private List<ReceiptInfoDTO> getReceiptSumCost() {
        String query = "select cast(sale_date as date) as sale_date, store_name,  " +
                "sum(cast(receipt.cost as decimal)) as sale_cost from receipt\n" +
                "group by store_name, sale_date\n" +
                "order by sale_date";

        return this.jdbcTemplate.query(query, new ReceiptInfoDTOMapper());
    }


    @GetMapping("/receiptData")
    private /*List<Double>*/ List<InfoWrapper> getReceiptSumCostData(@RequestParam String dateFrom, @RequestParam String dateTo) {
        LocalDate dateFromDate;
        LocalDate dateToDate;

        DateTimeFormatter formatterForREST = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        dateFromDate = LocalDate.parse(dateFrom, formatterForREST);
        dateToDate = LocalDate.parse(dateTo, formatterForREST);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern(DATE_FORMATTER_DAY);
        ArrayList<InfoWrapper> infoWrappers = new ArrayList<>();
        String query = "select cast(sale_date as date) as sale_date, store_name,  " +
                "sum(cast(receipt.cost as decimal)) as sale_cost from receipt\n" +
                " where store_name = ? and  cast(sale_date as date) >= ? and cast(sale_date as date) <= ? " +
                "group by store_name, cast(sale_date as date)\n" +
                "order by cast(sale_date as date)";
        int yearFrom = dateFromDate.getYear();
        int monthFrom = dateFromDate.getMonthValue();
        int dayFrom = dateFromDate.getDayOfMonth();
        int yearTo = dateToDate.getYear();
        int monthTo = dateToDate.getMonthValue();
        int dayTo = dateToDate.getDayOfMonth();

        List<LocalDate> localDateList = getDatesBetweenUsingJava8(
                LocalDate.of(yearFrom, monthFrom, dayFrom),
                LocalDate.of(yearTo, monthTo, dayTo));

        List<String> departmentList = userRepo.findAllDepartmentOrderByNameAsc();
        for (String department : departmentList) {

            List<ReceiptInfoDTO> receiptInfoDTOList = new ArrayList<>();
            receiptInfoDTOList.addAll(this.jdbcTemplate.query(query, new ReceiptInfoDTOMapper(),department, dateFromDate,dateToDate));
//        receiptInfoDTOList.addAll(this.jdbcTemplate.query(query, new ReceiptInfoDTOMapper(), store_name));

            List<Double> dataCost = new ArrayList<>();
            List<String> dateSale = new ArrayList<>();
            for (LocalDate localDate : localDateList) {


                for (ReceiptInfoDTO receiptInfoDTO : receiptInfoDTOList) {
                    if (receiptInfoDTO.getSaleDate().equals(localDate)) {
                        dataCost.add(Double.valueOf(receiptInfoDTO.getSaleCost()));
                        dateSale.add(localDate.format(formatter) + "(" + localDate.format(formatterDay) + ")");
                        break;
                    } else {
                        dataCost.add(Double.valueOf(0));
                        dateSale.add(localDate.format(formatter) + "(" + localDate.format(formatterDay) + ")");
                        break;
                    }

                }
            }
            infoWrappers.add(new InfoWrapper(dataCost, dateSale, department));
        }

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


    public static List<LocalDate> getDatesBetweenUsingJava8(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }


}