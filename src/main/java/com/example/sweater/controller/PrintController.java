package com.example.sweater.controller;

import com.example.sweater.domain.DistributionDoc;
import com.example.sweater.domain.NewLabel;
import com.example.sweater.domain.NewLabelDistrib;
import com.example.sweater.domain.Product;
import com.example.sweater.repos.DistributionDocRepo;
import com.example.sweater.repos.ProductRepo;
import com.itextpdf.text.DocumentException;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

public class PrintController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private DistributionDocRepo distributionDocRepo;

    @PostMapping("createpdf")
    public String myPrint(@RequestParam Product product) throws IOException {
        NewLabel documentPdf = new NewLabel(product);
        try {
            documentPdf.createPdf();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return "documentpdf";
    }

    @RequestMapping(value = "/getpdf", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getPDF(@RequestParam String filter, @RequestParam String count, Map<String, Object> model) throws IOException, DocumentException {
        Iterable<Product> products;
        ResponseEntity<byte[]> response = null;

        if (filter != null && !filter.isEmpty()) {
            products = productRepo.findByBarcodeOrderByIdAsc(filter);
            for (Product product : products) {
                NewLabel documentPdf = new NewLabel(product);
                byte[] contents = documentPdf.getPDF(Integer.parseInt(count));

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                // Here you have to set the actual filename of your pdf
                String filename = "file.pdf";
                headers.setContentDispositionFormData(filename, filename);
                headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
                response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
                return response;
            }
        } else {
            products = productRepo.findAllByOrderByIdDesc();


        }

        return response;
    }

    @RequestMapping(value = "/getDistribpPdf", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getDistribPDF(@RequestParam String filter,  Map<String, Object> model) throws IOException, DocumentException {


        List<DistributionDoc> distributionDocs;
        List<Product> products;
        List<Product> productsAll = new ArrayList<>();
        ResponseEntity<byte[]> response = null;
        int kol = 0;

        if (filter != null && !filter.isEmpty()) {
            distributionDocs = distributionDocRepo.findAllByDocNumber(Integer.parseInt(filter));

            for (DistributionDoc distributionDoc : distributionDocs) {
                products = productRepo.findByIdOrderByIdAsc(distributionDoc.getProduct_id());

                for (Product product : products) {
                  productsAll.add(product);
                    kol++;
                    System.out.println(kol);
                }
            }


            NewLabelDistrib documentPdf = new NewLabelDistrib(distributionDocs, productsAll);
            byte[] contents = documentPdf.getPDF(Integer.parseInt("1"));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Here you have to set the actual filename of your pdf
            String filename = "file.pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
            return response;


        } else {
            products = productRepo.findAllByOrderByIdDesc();


        }

        return response;

    }
}
