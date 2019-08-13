package com.example.sweater.controller;


import com.example.sweater.domain.DistributionDoc;
import com.example.sweater.repos.DistributionDocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class DistributionDocController {
    @Autowired
    private DistributionDocRepo distributionDocRepo;

    @PostMapping("/addDistrDoc")
    public String addDistrDoc(@RequestParam String distrArray, Map<String, Object> model) {

        Date distrDateTime = new Date();
        String s = distrArray;
        String productArr[] = s.split(";");
        String productDetailArr[];
        DistributionDoc distributionDoc;
        Integer productCounter = 0;
        productCounter = productArr.length;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Integer docNumber =  distributionDocRepo.findFirstByOrderByDocNumberDesc().getDocNumber() + 1;

        for (String s1 : productArr) {

            productDetailArr = null;
            productDetailArr = s1.split(":");



            /*
            *
            *    document.getElementsByClassName("id")[i].innerText + ":" +
                 document.getElementsByName("all")[i].value + ":" +
                 document.getElementsByName("m1")[i].value + ":" +
                 document.getElementsByName("m2")[i].value + ":" +
                 document.getElementsByName("m3")[i].value + ":" +
                 document.getElementsByName("m4")[i].value + ":" +
                 document.getElementsByName("m5")[i].value + ":" +
                 document.getElementsByName("m6")[i].value + ":" +
                 document.getElementsByName("m7")[i].value  + ":" +
                 document.getElementsByName("sklad")[i].value + ";"

            * */


            distributionDoc = new DistributionDoc(
                    docNumber, distrDateTime, "user1",
                    Integer.parseInt(productDetailArr[9]),
                    Integer.parseInt(productDetailArr[2]),
                    Integer.parseInt(productDetailArr[3]),
                    Integer.parseInt(productDetailArr[4]),
                    Integer.parseInt(productDetailArr[5]),
                    Integer.parseInt(productDetailArr[6]),
                    Integer.parseInt(productDetailArr[7]),
                    Integer.parseInt(productDetailArr[8]),
                    Integer.parseInt(productDetailArr[9]),
                    Long.parseLong(productDetailArr[0]),
                    format.format(distrDateTime)


            );


            distributionDocRepo.save(distributionDoc);

            Iterable<DistributionDoc> distributionDocs = distributionDocRepo.findAll();
            model.put("distributionDocs", distributionDocs);


            Iterable<DistributionDoc> distributionCurrentDocs = distributionDocRepo.findAllByDocNumber(docNumber);
            model.put("distributionCurrentDocs", distributionCurrentDocs);

        }


        return "distributionResult";
    }

}
