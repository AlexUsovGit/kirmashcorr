package com.example.sweater.controller;

import com.example.sweater.domain.Receipt;
import com.example.sweater.domain.basedictionary.Composition;
import com.example.sweater.repos.ReceiptRepo;
import com.example.sweater.repos.basedictionaryrepos.CompositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Controller
public class CommonController {
    @Autowired
    private CompositionRepo compositionRepo;
    @Autowired
    private ReceiptRepo receiptRepo;



    @GetMapping("/compositionJSON")
    public List<Composition> compositionJSON() {
        List<Composition> compositions = (List) compositionRepo.findAll();
        return compositions;
    }



    @GetMapping("/receiptJSON")
    public List<Receipt> receiptJSON() {
        List<Receipt> receipts = (List) receiptRepo.findAll();
        return receipts;
    }

    @GetMapping("/receiptCostJSON")
    public List<Double> receiptCostJSON() {
        List<Double> receiptCosts = (List) receiptRepo.findAllCost();

        return receiptCosts;
    }
}


