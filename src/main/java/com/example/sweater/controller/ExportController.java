package com.example.sweater.controller;


import com.example.sweater.domain.Product;
import com.example.sweater.domain.TableNames;
import com.example.sweater.export.Exports;
import com.example.sweater.export.ExportsTables;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.TableNamesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ExportController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private TableNamesRepo tableNamesRepo;



    @PostMapping("/exportAllProducts")
    public String exportAllProducts(Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAllByOrderByIdDesc();

        model.put("products", products);
        Exports exports = new Exports();
        exports.createXlsx(products);


        return "producttable";
    }

    @RequestMapping(value = "/getProducts", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getExcel(@RequestParam String myfilter) throws IOException {
        List<Product> products = new ArrayList<>();




        ResponseEntity<byte[]> response = null;
        if(myfilter != null && !myfilter.isEmpty()){
            products.addAll(productRepo.findByBarcode(myfilter));
            products.addAll(productRepo.findByFilterOrderByIdAsc(myfilter));
            products.addAll(productRepo.findByGenderOrderByIdAsc(myfilter));
            products.addAll(productRepo.findByTrademarkOrderByIdAsc(myfilter));
            products.addAll(productRepo.findBySeasonOrderByIdAsc(myfilter));
            products.addAll(productRepo.findByBoxNumberOrderByIdAsc(myfilter));
        }else{
            products = productRepo.findAllByOrderByIdDesc();
        }


/*
        Exports exports = new Exports();
        exports.createXlsx(products);
        byte[] contents = exports.getXLS2();     */
        ExportsTables exportsTables = new ExportsTables();
        exportsTables.createXlsx2(products);
        byte[] contents = exportsTables.getXLS2();


        HttpHeaders headers = new HttpHeaders();
       // headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentType(MediaType.APPLICATION_XML);
        // Here you have to set the actual filename of your pdf
        String filename = "products.xlsx";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        response = new ResponseEntity<>(contents, headers, HttpStatus.OK);


        return response;


    }


    @RequestMapping(value = "/getAlltables", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getExcelTables(@RequestParam String myfilter) throws IOException {
        List<TableNames> tableNames = tableNamesRepo.findAllInfo();
        for (TableNames tableName : tableNames) {

        }
        List<Product> products = new ArrayList<>();




        ResponseEntity<byte[]> response = null;
        if(myfilter != null && !myfilter.isEmpty()){
            products.addAll(productRepo.findByBarcode(myfilter));
            products.addAll(productRepo.findByFilterOrderByIdAsc(myfilter));
            products.addAll(productRepo.findByGenderOrderByIdAsc(myfilter));
            products.addAll(productRepo.findByTrademarkOrderByIdAsc(myfilter));
            products.addAll(productRepo.findBySeasonOrderByIdAsc(myfilter));
            products.addAll(productRepo.findByBoxNumberOrderByIdAsc(myfilter));
        }else{
            products = productRepo.findAllByOrderByIdDesc();
        }



        ExportsTables exportsTables = new ExportsTables();
        exportsTables.createXlsx2(products);
        byte[] contents = exportsTables.getXLS2();


        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentType(MediaType.APPLICATION_XML);
        // Here you have to set the actual filename of your pdf
        String filename = "products.xlsx";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        response = new ResponseEntity<>(contents, headers, HttpStatus.OK);


        return response;


    }
    }
