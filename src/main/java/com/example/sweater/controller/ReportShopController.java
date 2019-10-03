package com.example.sweater.controller;

import com.example.sweater.domain.*;
import com.example.sweater.repos.ReceiptNumberRepo;
import com.example.sweater.repos.ReceiptRepo;
import com.example.sweater.repos.ReportShopReceiptFilterRepo;
import com.example.sweater.repos.UserRepo;
import com.example.sweater.service.AuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ReportShopController {


    @Autowired
    private ReceiptRepo receiptRepo;
    @Autowired
    ReceiptNumberRepo receiptNumberRepo;
    @Autowired
    AuthenticationInfo authenticationInfo;
    @Autowired
    ReportShopReceiptFilterRepo reportShopReceiptFilterRepo;


    @PostMapping("/reportPage")
    public String reportPage(Map<String, Object> model) {

        int productCounter = 0;

        double allCost = 0.00;
        String currentUserName;
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        currentUserName = authenticationInfo.getCurrentUser().getUsername();
        ReceiptNumber receiptNumber = new ReceiptNumber(currentUserName, today, "temp");
        receiptNumberRepo.save(receiptNumber);
        Iterable<Receipt> allReceipt =
                receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUserName, formatter.format(today));


        model.put("currentUserName", currentUserName);
        model.put("department", authenticationInfo.getCurrentUser().getStoreName());
        model.put("receiptNumber", receiptNumber.getId());
        model.put("productCounter", productCounter);
        model.put("AllCost", allCost);
        model.put("allReceipt", allReceipt);
        model.put("currentProductCounter", getCurrentProductCounter(allReceipt));
        model.put("currentSummCost", getCurrentSummCost(allReceipt));

        model.putAll(authenticationInfo.getPermission(model));

        return "reportpage";
    }


    @PostMapping("/reportShopReceipt")
    public String reportShopReceipt(@RequestParam String dateFrom, @RequestParam String dateTo,
                                    @RequestParam String department, Map<String, Object> model) throws ParseException {

        int productCounter = 0;
        double allCost = 0.00;
        String currentUserDepartment, correctDepartment;
        Date dateFromDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
        Date dateToDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
        if(department.equals(" - Отобразить все - ")){
            correctDepartment = "";
        }else{
            correctDepartment = department;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        currentUserDepartment = authenticationInfo.getCurrentUser().getStoreName();
        Iterable<Receipt> allReceipt;
        if (authenticationInfo.getCurrentUser().isShowAdmin()) {
//            allReceipt = receiptRepo.findAllOrderBySaleDateDesc(formatter.format(todayDate));
            allReceipt = receiptRepo.findAllBySaleDate2AndDepartmentOrderBySaleDateDesc(formatter.format(dateFromDate),
                    formatter.format(dateToDate), correctDepartment);

            model.putAll(authenticationInfo.getDepartmentList(model));
        } else {
          /*  allReceipt = receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUserName, formatter.format(dateFromDate));*/
            allReceipt = receiptRepo.findAllBySaleDate2AndDepartmentOrderBySaleDateDesc(formatter.format(dateFromDate),
                    formatter.format(dateToDate), currentUserDepartment);
            List<String> departmentList = new ArrayList<>();

            departmentList.add(authenticationInfo.getCurrentUser().getStoreName());
            model.put("departmentList", departmentList);

        }
        ReportShopReceiptFilter reportShopReceiptFilter = new ReportShopReceiptFilter(department, dateFrom, dateTo);
        reportShopReceiptFilterRepo.save(reportShopReceiptFilter);

        ReportShopReceiptFilter reportShopReceiptFilterRestore = reportShopReceiptFilterRepo.findFirstByOrderById();

        model.put("reportShopReceiptFilterRestore", reportShopReceiptFilterRestore.getDateFrom());
        model.put("currentUserName", currentUserDepartment);

        model.put("dateFrom", reportShopReceiptFilterRestore.getDateFrom());
        model.put("dateTo", reportShopReceiptFilterRestore.getDateTo());
        model.put("department", reportShopReceiptFilterRestore.getDepartment());

        model.put("productCounter", productCounter);
        model.put("AllCost", allCost);
        model.put("allReceipt", allReceipt);
        model.put("currentProductCounter", getCurrentProductCounter(allReceipt));
        model.put("currentSummCost", getCurrentSummCost(allReceipt));

        model.putAll(authenticationInfo.getPermission(model));




        return "reportShopReceipt";
    }


    @PostMapping("/reportShopReceiptChart")
    public String reportShopReceiptChart(@RequestParam String dateFrom, @RequestParam String dateTo,
                                    @RequestParam String department, Map<String, Object> model) throws ParseException {

        int productCounter = 0;
        double allCost = 0.00;
        String currentUserDepartment, correctDepartment;
        Date dateFromDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
        Date dateToDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
        if(department.equals(" - Отобразить все - ")){
            correctDepartment = "";
        }else{
            correctDepartment = department;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        currentUserDepartment = authenticationInfo.getCurrentUser().getStoreName();
        Iterable<Receipt> allReceipt;
        if (authenticationInfo.getCurrentUser().isShowAdmin()) {
//            allReceipt = receiptRepo.findAllOrderBySaleDateDesc(formatter.format(todayDate));
            allReceipt = receiptRepo.findAllBySaleDate2AndDepartmentOrderBySaleDateDesc(formatter.format(dateFromDate),
                    formatter.format(dateToDate), correctDepartment);

            model.putAll(authenticationInfo.getDepartmentListV(model));
        } else {
            /*  allReceipt = receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUserName, formatter.format(dateFromDate));*/
            allReceipt = receiptRepo.findAllBySaleDate2AndDepartmentOrderBySaleDateDesc(formatter.format(dateFromDate),
                    formatter.format(dateToDate), currentUserDepartment);
            List<String> departmentList = new ArrayList<>();
            departmentList.add(authenticationInfo.getCurrentUser().getStoreName());
            model.put("departmentList", departmentList);

        }
        ReportShopReceiptFilter reportShopReceiptFilter = new ReportShopReceiptFilter(department, dateFrom, dateTo);
        reportShopReceiptFilterRepo.save(reportShopReceiptFilter);

        ReportShopReceiptFilter reportShopReceiptFilterRestore = reportShopReceiptFilterRepo.findFirstByOrderById();

        model.put("reportShopReceiptFilterRestore", reportShopReceiptFilterRestore.getDateFrom());
        model.put("currentUserName", currentUserDepartment);

        model.put("dateFrom", reportShopReceiptFilterRestore.getDateFrom());
        model.put("dateTo", reportShopReceiptFilterRestore.getDateTo());
        model.put("department", reportShopReceiptFilterRestore.getDepartment());

        model.put("productCounter", productCounter);
        model.put("AllCost", allCost);
        model.put("allReceipt", allReceipt);
        model.put("currentProductCounter", getCurrentProductCounter(allReceipt));
        model.put("currentSummCost", getCurrentSummCost(allReceipt));

        model.putAll(authenticationInfo.getPermission(model));




        return "reportShopReceiptChart";
    }

    public int getCurrentProductCounter(Iterable<Receipt> allReceipt) {
        int currentProductCounter = 0;
        for (Receipt receipt : allReceipt) {
            currentProductCounter = currentProductCounter + Integer.parseInt(receipt.getCount());
        }
        return currentProductCounter;
    }

    public double getCurrentSummCost(Iterable<Receipt> allReceipt) {
        double currentSummCost = 0.00;
        for (Receipt receipt : allReceipt) {
            currentSummCost = currentSummCost + Double.parseDouble(receipt.getCost());
        }
        return Math.round(currentSummCost * 100.0) / 100.0;
    }


}
