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

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        model.put("receiptNumber", receiptNumber.getId());
        model.put("productCounter", productCounter);


        return "shop";
    }


    @PostMapping("/findProduct")
    public String productFilter(@RequestParam String myfilter, Map<String, Object> model) {

        /*  DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");*/
        Date date = new Date();
        ReceiptNumber receiptNumber = receiptNumberRepo.findFirst1ByOrderByIdDesc();
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



            model.put("showProduct", true);
            model.put("showExeption", false);


        } else {
            model.put("showProduct", false);
            model.put("showExeption", true);
        }

        Iterable<Receipt> receipts = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber);
        productCounter = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber).size();
        model.put("receipts", receipts);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        model.put("receiptNumber", receiptNumber.getId());
        model.put("productCounter", productCounter);


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
    public String deleteFromReceipt(@RequestParam String myfilter, @RequestParam String id, Map<String, Object> model) {

        /*  DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");*/
        Date date = new Date();
        ReceiptNumber receiptNumber = receiptNumberRepo.findFirst1ByOrderByIdDesc();
        String currentReceiptNumber = String.valueOf(receiptNumber.getId());
        Integer productCounter = 0;
        Long longId = new Long(id);
        receiptRepo.deleteById(longId);
        List<Product> products = new ArrayList<>();
        products.addAll(productRepo.findByBarcodeOrderByIdAsc(myfilter.toUpperCase()));


        Iterable<Receipt> receipts = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber);
        productCounter = receiptRepo.findAllByReceiptNumberOrderBySaleDateDesc(currentReceiptNumber).size();
        model.put("receipts", receipts);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);

        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        model.put("receiptNumber", receiptNumber.getId());
        model.put("productCounter", productCounter);


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
