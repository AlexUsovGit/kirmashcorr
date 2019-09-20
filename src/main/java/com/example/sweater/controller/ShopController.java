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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController {
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


    @GetMapping("/shop")
    public String show(Map<String, Object> model) {
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
                receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUser.getUsername(), formatter.format(today));
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


        return "shop";
    }


    @PostMapping("/findProduct")
    public String productFilter(@RequestParam String myfilter, Map<String, Object> model) {
        int currentProductCounter = 0;
        double currentSummCost = 0.00;
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Long id;
        Double AllCost = 0.00;


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        /*  DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");*/
        Date date = new Date();
        ReceiptNumber receiptNumber = receiptNumberRepo.findFirst1ByAuthorOrderByIdDesc(currentUser.getUsername());
        String currentReceiptNumber = String.valueOf(receiptNumber.getId());
        Integer productCounter = 0;

        List<Product> products = new ArrayList<>();
        products.addAll(productRepo.findByBarcodeOrderByIdAsc(myfilter.toUpperCase()));
        if (products.size() > 0) {
            currentProduct = productRepo.findFirst1ByBarcode(myfilter.toUpperCase());
            Receipt receipt = new Receipt(currentProduct.getProductName(), currentReceiptNumber, currentProduct.getBarcode(),
                    currentProduct.getRetailPrice(), date, "0", "0",
                    currentProduct.getRetailPrice(), "temp", currentProduct.getGender());
            receiptRepo.save(receipt);
            id = receipt.getId();

            model.put("id", id);
            model.put("showProduct", true);
            model.put("showExeption", false);


        } else {
            model.put("showProduct", false);
            model.put("showExeption", true);
        }

        Iterable<Receipt> receipts = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber);
        productCounter = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber).size();
        model.put("receipts", receipts);
        for (Receipt receipt1 : receipts) {

            AllCost = AllCost + Double.parseDouble(receipt1.getCost());
        }


        Iterable<Receipt> allReceipt =
                receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUser.getUsername(), formatter.format(today));
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


        Filter filter = new Filter(myfilter);
        filterRepo.save(filter);
        String filterValue = "";

        Iterable<Filter> filters = filterRepo.findAll();
        for (Filter filter1 : filters) {
            filterValue = filter1.getValue();
        }
        model.put("filterValue", filterValue);

        return "shop";
    }

    @PostMapping("/deleteFromReceipt")
    public String deleteFromReceipt(
            @RequestParam String myfilter, @RequestParam String id,
            Map<String, Object> model) {
        int currentProductCounter = 0;
        double currentSummCost = 0.00;
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        /*  DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");*/
        Double AllCost = 0.00;
        ReceiptNumber receiptNumber = receiptNumberRepo.findFirst1ByAuthorOrderByIdDesc(currentUser.getUsername());
        String currentReceiptNumber = String.valueOf(receiptNumber.getId());
        Integer productCounter = 0;
        Long longId = new Long(id);
        receiptRepo.deleteById(longId);
        List<Product> products = new ArrayList<>();
        products.addAll(productRepo.findByBarcodeOrderByIdAsc(myfilter.toUpperCase()));


        Iterable<Receipt> receipts = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber);
        productCounter = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber).size();
        model.put("receipts", receipts);
        for (Receipt receipt1 : receipts) {

            AllCost = AllCost + Double.parseDouble(receipt1.getCost());
        }


        Iterable<Receipt> allReceipt =
                receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUser.getUsername(), formatter.format(today));
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


        Filter filter = new Filter(myfilter);
        filterRepo.save(filter);
        String filterValue = "";

        Iterable<Filter> filters = filterRepo.findAll();
        for (Filter filter1 : filters) {
            filterValue = filter1.getValue();
        }
        model.put("filterValue", filterValue);

        return "shop";
    }

    @GetMapping("/updateIntoReceipt")
    public String updateIntoReceipt(
            @RequestParam String myfilter, @RequestParam String id,
            @RequestParam String count, @RequestParam String discount,
            Map<String, Object> model) {
        int currentProductCounter = 0;
        double currentSummCost = 0.00;
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Double AllCost = 0.00;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        ReceiptNumber receiptNumber = receiptNumberRepo.findFirst1ByAuthorOrderByIdDesc(currentUser.getUsername());
        String currentReceiptNumber = String.valueOf(receiptNumber.getId());
        Integer productCounter = 0;
        Long longId = new Long(id);
        Receipt receiptCurrent = receiptRepo.findFirstById(longId);
        receiptCurrent.setCount(count);
        receiptCurrent.setDiscount(discount);
        double newCost = 0.00;
        newCost = Double.parseDouble(receiptCurrent.getRetailPrice()) * (1 - (double)Integer.parseInt(discount)/100)
                * Integer.parseInt(count)*100.0;
        double roundNewCost = Math.round(newCost)/100.0;
        receiptCurrent.setCost(String.valueOf(roundNewCost));
        receiptRepo.save(receiptCurrent);

        List<Product> products = new ArrayList<>();
        products.addAll(productRepo.findByBarcodeOrderByIdAsc(myfilter.toUpperCase()));


        Iterable<Receipt> receipts = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber);
        productCounter = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber).size();
        for (Receipt receipt1 : receipts) {

            AllCost = AllCost + Double.parseDouble(receipt1.getCost());
        }
        model.put("receipts", receipts);


        Iterable<Receipt> allReceipt =
                receiptRepo.findAllByAuthorOrderBySaleDateDesc(currentUser.getUsername(), formatter.format(today));
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


        Filter filter = new Filter(myfilter);
        filterRepo.save(filter);
        String filterValue = "";

        Iterable<Filter> filters = filterRepo.findAll();
        for (Filter filter1 : filters) {
            filterValue = filter1.getValue();
        }
        model.put("filterValue", filterValue);

        return "shop";
    }

}
