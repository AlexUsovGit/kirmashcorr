package com.example.sweater.controller;

import com.example.sweater.domain.*;
import com.example.sweater.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FilterRepo filterRepo;
    @Autowired
    private ReceiptRepo receiptRepo;
    @Autowired
    ReceiptNumberRepo receiptNumberRepo;

    private int AllCounter;
    private int PageCounter;
    private Product currentProduct;

    @GetMapping("/reportPage")
    public String reportPage(Map<String, Object> model) {
        int currentProductCounter = 0;
        double currentSummCost = 0.00;

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Double AllCost = 0.00;
        Integer productCounter = 0;
        Iterable<Product> products = null;
        /*       products= productRepo.findFirst50ByOrderByIdDesc();*/
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);
        Date date = new Date();
        ReceiptNumber receiptNumber = new ReceiptNumber(name, date, "temp");
        receiptNumberRepo.save(receiptNumber);
        Iterable<Receipt> allReceipt =
                receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUser.getUsername(),formatter.format(today));
        for (Receipt receipt : allReceipt) {
            currentProductCounter = currentProductCounter + Integer.parseInt(receipt.getCount());
            currentSummCost = currentSummCost + Double.parseDouble(receipt.getCost());
        }

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        model.put("receiptNumber", receiptNumber.getId());
        model.put("productCounter", productCounter);
        model.put("AllCost", AllCost);
        model.put("allReceipt", allReceipt);
        model.put("currentProductCounter", currentProductCounter);
        model.put("currentSummCost", currentSummCost);


        return "reportpage";
    }



    @GetMapping("/reportShopReceipt")
    public String reportShopReceipt(@RequestParam String today, Map<String, Object> model) throws ParseException {
        int currentProductCounter = 0;
        double currentSummCost = 0.00;
        Date today2=new SimpleDateFormat("yyyy-MM-dd").parse(today);
  /*     Date today2 = new Date(today);*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Double AllCost = 0.00;
        Integer productCounter = 0;
        Iterable<Product> products = null;
        /*       products= productRepo.findFirst50ByOrderByIdDesc();*/
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);
        Date date = new Date();
        ReceiptNumber receiptNumber = new ReceiptNumber(name, date, "temp");
        receiptNumberRepo.save(receiptNumber);
        Iterable<Receipt> allReceipt =
                receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUser.getUsername(),formatter.format(today2));
        for (Receipt receipt : allReceipt) {
            currentProductCounter = currentProductCounter + Integer.parseInt(receipt.getCount());
            currentSummCost = currentSummCost + Double.parseDouble(receipt.getCost());
        }

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        model.put("receiptNumber", receiptNumber.getId());
        model.put("productCounter", productCounter);
        model.put("AllCost", AllCost);
        model.put("allReceipt", allReceipt);
        model.put("currentProductCounter", currentProductCounter);
        model.put("currentSummCost", currentSummCost);


        return "reportShopReceipt";
    }




}
