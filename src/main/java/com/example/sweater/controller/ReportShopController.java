package com.example.sweater.controller;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.Receipt;
import com.example.sweater.domain.ReceiptNumber;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ReceiptNumberRepo;
import com.example.sweater.repos.ReceiptRepo;
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
import java.util.Date;
import java.util.Map;

@Controller
public class ReportShopController {


    @Autowired
    private ReceiptRepo receiptRepo;
    @Autowired
    ReceiptNumberRepo receiptNumberRepo;
    @Autowired
    AuthenticationInfo authenticationInfo;

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
        model.put("receiptNumber", receiptNumber.getId());
        model.put("productCounter", productCounter);
        model.put("AllCost", allCost);
        model.put("allReceipt", allReceipt);
        model.put("currentProductCounter",  getCurrentProductCounter(allReceipt));
        model.put("currentSummCost", getCurrentSummCost(allReceipt));

        model.putAll(authenticationInfo.getPermission(model));

        return "reportpage";
    }


    @PostMapping("/reportShopReceipt")
    public String reportShopReceipt(@RequestParam String today,
                                    @RequestParam String department ,Map<String, Object> model) throws ParseException {

        int productCounter = 0;
        double allCost = 0.00;
        String currentUserName;
        Date todayDate = new SimpleDateFormat("yyyy-MM-dd").parse(today);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        currentUserName = authenticationInfo.getCurrentUser().getUsername();
        Iterable<Receipt> allReceipt;
        if (authenticationInfo.getCurrentUser().isShowAdmin()) {
//            allReceipt = receiptRepo.findAllOrderBySaleDateDesc(formatter.format(todayDate));
            allReceipt = receiptRepo.findAllBySaleDateAndDepartmentOrderBySaleDateDesc(formatter.format(todayDate),department);
        } else {
            allReceipt = receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUserName, formatter.format(todayDate));
        }
        model.put("currentUserName", currentUserName);
        model.put("productCounter", productCounter);
        model.put("AllCost", allCost);
        model.put("allReceipt", allReceipt);
        model.put("currentProductCounter",  getCurrentProductCounter(allReceipt));
        model.put("currentSummCost", getCurrentSummCost(allReceipt));
        model.putAll(authenticationInfo.getPermission(model));

        return "reportShopReceipt";
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
        return Math.round(currentSummCost*100.0)/100.0;
    }


}
