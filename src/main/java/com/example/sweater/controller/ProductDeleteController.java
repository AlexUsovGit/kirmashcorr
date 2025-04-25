package com.example.sweater.controller;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ProductDeleteController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/productDelete")
    public String productDelete(String filter, Map<String, Object> model) {
        Product product = productRepo.findFirst1ByBarcode(filter);
        long recordsCount;
        long recordsOnPageCount;
        productRepo.delete(product);
        List<Product> products = productRepo.findFirst50ByOrderByIdDesc();
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findFirstByUsername(name);
        recordsCount = productRepo.count();
        //   FiltredCounter = productRepo.findFirst50ByOrderByIdDesc().size();
        recordsOnPageCount = products.size();
        model.put("recordsCount", recordsCount);
        model.put("recordsOnPageCount", recordsOnPageCount);
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        String filterValue = "";
        model.put("filterValue", filterValue);


        return "producttable";
    }
}
