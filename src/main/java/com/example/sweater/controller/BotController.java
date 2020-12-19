package com.example.sweater.controller;

import com.example.sweater.dto.CommonInfoDTO;
import com.example.sweater.repos.ReceiptNumberRepo;
import com.example.sweater.repos.ReceiptRepo;
import com.example.sweater.repos.ReportShopReceiptFilterRepo;
import com.example.sweater.service.AuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report/bot")
public class BotController {


    @Autowired
    private ReceiptRepo receiptRepo;
    @Autowired
    ReceiptNumberRepo receiptNumberRepo;
    @Autowired
    AuthenticationInfo authenticationInfo;
    @Autowired
    ReportShopReceiptFilterRepo reportShopReceiptFilterRepo;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/store")
    public String reportShopReceipt() {
        LocalDate date = LocalDate.now();
        List<CommonInfoDTO> info = getCommonInfoDTO(date);
        return getMessageString(info, date);
    }
    @GetMapping("/store/{date}")
    public String reportShopReceiptByDate(@PathVariable String date) {
        LocalDate requestDate =  LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        List<CommonInfoDTO> info = getCommonInfoDTO(requestDate);
        return getMessageString(info, requestDate);
    }

    List<CommonInfoDTO> getCommonInfoDTO(LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Object> params = new ArrayList<>();
        params.add(dateTimeFormatter.format(date));
        String query = "SELECT r.store_name as store, sum(cast(r.count as float8)) as counting, sum(cast(r.cost as float8)) as summa from  receipt  r  " +
                "         left JOIN receipt_number rn on  CAST(r.receipt_number as int8) = rn.id  " +
                "         where Cast(r.sale_date as date) =  Cast(? as date)  " +
                "         GROUP BY r.store_name " +
                "         order by r.store_name asc";
        return jdbcTemplate.query(query, (rs, i) -> new CommonInfoDTO(
                rs.getString("store"),
                rs.getDouble("counting"),
                rs.getDouble("summa")
        ), params.toArray());
    }

    String getMessageString(List<CommonInfoDTO> info, LocalDate date) {
        DateTimeFormatter dateExportFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        StringBuilder builder = new StringBuilder();
        builder.append("Данные по продажам за \\n");
        builder.append(dateExportFormatter.format(date));
        builder.append("\\n");
        builder.append("*Магазин*").append("\\t");
        builder.append("*Кол-во*").append("\\t");
        builder.append("*Сумма*").append("\\t");
        builder.append("\\n");
        for (CommonInfoDTO dto : info) {
            builder.append(dto.getStore()).append(" \\t ");
            builder.append(Math.round(dto.getCounting()*100)/100).append(" \\t шт ");
            builder.append(Math.round(dto.getSumma()*100)/100).append(" \\t BYN");
            builder.append("\\n");
        }
        return builder.toString();
    }
}
