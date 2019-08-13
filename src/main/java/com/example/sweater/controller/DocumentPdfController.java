package com.example.sweater.controller;


import com.example.sweater.domain.DocumentPdf;
import com.example.sweater.repos.DocumentpdfRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DocumentPdfController {
    @Autowired
    private DocumentpdfRepo documentpdfRepo;

    @GetMapping("/documentpdf")
    public String documentpdf (Map<String, Object> model) {
        Iterable<DocumentPdf> documentPdfs = documentpdfRepo.findAll();
        model.put("documentPdfs",documentPdfs);



        return "documentpdf";
    }


    @PostMapping("filterdocumentpdf")
    public String filter (@RequestParam String filter, Map<String, Object> model){
        Iterable<DocumentPdf> documentPdfs;

        if(filter!=null && !filter.isEmpty()){
            documentPdfs = documentpdfRepo.findByBarcode(filter);

        }else {
           documentPdfs = documentpdfRepo.findAll();
        }
        model.put("documentPdfs", documentPdfs);

        return "documentpdf";
    }

}
