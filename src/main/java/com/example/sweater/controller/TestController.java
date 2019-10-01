package com.example.sweater.controller;


import com.example.sweater.domain.InfoDTO;
import com.example.sweater.dto.InfoWrapper;
import com.example.sweater.dto.ReceiptInfoDTO;
import com.example.sweater.dto.StoreNameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("test/api")
public class TestController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String DATE_FORMATTER = "dd.MM";

    @GetMapping("/product/count")
    private int getProductCount() {
        int result = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM product", Integer.class);
        return result;
    }

    @GetMapping("/products")
    private List<InfoDTO> getProductsCount() {
/*        String query = "select product_name, count(id) as product_count  from product " +
                "group by product_name";
        InfoDTO infoDTOS = jdbcTemplate.queryForList (query, new InfoDTORowMapper());

    return infoDTOS;*/
        return findAllActors();
    }

    public List<InfoDTO> findAllActors() {
        return this.jdbcTemplate.query("select product_name, count(id) as product_count  from product \" +\n" +
                "                \"group by product_name", new ActorMapper());
    }

    private /*static final*/ class ActorMapper implements RowMapper<InfoDTO> {

        public InfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            InfoDTO infoDTO = new InfoDTO();
            infoDTO.setProductName(rs.getString("product_name"));
            infoDTO.setProductCount(rs.getInt("product_count"));
            return infoDTO;
        }
    }


    @GetMapping("/receiptByDate")
    private List<ReceiptInfoDTO> getReceiptSumCost() {
        String query = "select cast(sale_date as date) as sale_date, store_name,  " +
                "sum(cast(receipt.cost as decimal)) as sale_cost from receipt\n" +
                "group by store_name, sale_date\n" +
                "order by sale_date";

        return this.jdbcTemplate.query(query, new ReceiptInfoDTOMapper());


    }

    @GetMapping("/receiptData")
    private /*List<Double>*/ List<InfoWrapper> getReceiptSumCostData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        ArrayList<InfoWrapper> infoWrappers = new ArrayList<>();
        String query = "select cast(sale_date as date) as sale_date, store_name,  " +
                "sum(cast(receipt.cost as decimal)) as sale_cost from receipt\n" +
                "group by store_name, cast(sale_date as date)\n" +
                "order by cast(sale_date as date)";

        List<ReceiptInfoDTO> receiptInfoDTOList = new ArrayList<>();
        receiptInfoDTOList.addAll(this.jdbcTemplate.query(query, new ReceiptInfoDTOMapper()));
        List<StoreNameDTO> storeNameDTOList = jdbcTemplate.query(
                "SELECT distinct store_name FROM receipt", new StoreNameDTOMapper());
//        InfoWrapper infoWrapper = new InfoWrapper();

        List<LocalDate> listOfDate = getDatesBetween(LocalDate.of(2019, 9, 1), LocalDate.of(2019, 10, 1));


        for (int i = 0; i < storeNameDTOList.size(); i++) {
            List<Double> dataCost = new ArrayList<>();
            List<String> dateSale = new ArrayList<>();
            String storeName = storeNameDTOList.get(i).getStoreName();
            for (LocalDate localDate : listOfDate) {
                for (ReceiptInfoDTO receiptInfoDTO : receiptInfoDTOList) {

                    if (receiptInfoDTO.getStoreName().equals(storeName)) {
                        if (receiptInfoDTO.getSaleDate().equals(localDate.toString())) {
                            dataCost.add(Double.valueOf(receiptInfoDTO.getSaleCost()));
                            dateSale.add(localDate.format(formatter));
                        } else {
                            dataCost.add(0.0);
                            dateSale.add(localDate.format(formatter));
                        }
                    } else {

                    }


                }
            }

            infoWrappers.add(new InfoWrapper(dataCost, dateSale));
        }


        /*

        for (LocalDate localDate : listOfDate) {

        for (int i = 0; i < storeNameDTOList.size(); i++) {
            List<Double> dataCost = new ArrayList<>();
            List<String> dateSale = new ArrayList<>();
            for (ReceiptInfoDTO receiptInfoDTO : receiptInfoDTOList) {

            String storeName = storeNameDTOList.get(i).getStoreName();

                if(receiptInfoDTO.getSaleDate().equals(localDate.toString()) & receiptInfoDTO.getStoreName().equals(storeName)) {

                    dataCost.add(Double.valueOf(receiptInfoDTO.getSaleCost()));
                    dateSale.add(localDate.format(formatter));

                }else{
                    dataCost.add(0.0);
                    dateSale.add(localDate.format(formatter));
                }


            }
            infoWrappers.add(new InfoWrapper(dataCost, dateSale));
            }

        }*/

        return /*dataCost*/ infoWrappers;


    }

    private class ReceiptInfoDTOMapper implements RowMapper<ReceiptInfoDTO> {

        public ReceiptInfoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReceiptInfoDTO receiptInfoDTO = new ReceiptInfoDTO();
            receiptInfoDTO.setSaleDate(rs.getString("sale_date"));
            receiptInfoDTO.setStoreName(rs.getString("store_name"));
            receiptInfoDTO.setSaleCost(rs.getString("sale_cost"));
            return receiptInfoDTO;
        }
    }


    private class StoreNameDTOMapper implements RowMapper<StoreNameDTO> {

        public StoreNameDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            StoreNameDTO storeNameDTO = new StoreNameDTO();
            storeNameDTO.setStoreName(rs.getString("store_name"));

            return storeNameDTO;
        }
    }

    @GetMapping("/storename")
    private List<StoreNameDTO> getStoreName() {
        List<StoreNameDTO> result = jdbcTemplate.query(
                "SELECT distinct store_name FROM receipt", new StoreNameDTOMapper());
        return result;
    }

    @GetMapping("/getDateList")
    private List<LocalDate> getDateList() {

        return getDatesBetween(LocalDate.of(2019, 9, 1), LocalDate.of(2019, 10, 1));
    }


    public static List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }

}
