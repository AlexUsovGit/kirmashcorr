package com.example.sweater.controller;

import com.example.sweater.domain.Product;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class ProductDelete {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    private int AllCounter;
    private int PageCounter;

    @PostMapping("/productDelete")
    public String productDelete(String filter, Map<String, Object> model) {
        Product  product = productRepo.findFirst1ByBarcode(filter);

        productRepo.delete(product);
        Iterable<Product> products = productRepo.findFirst50ByOrderByIdDesc();
        model.put("products", products);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User currentUser = userRepo.findByUsername(name);
        AllCounter = productRepo.findAllByOrderByIdDesc().size();
        //   FiltredCounter = productRepo.findFirst50ByOrderByIdDesc().size();
        PageCounter = productRepo.findFirst50ByOrderByIdDesc().size();
        model.put("AllCounter", AllCounter);
        model.put("PageCounter", PageCounter);
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
