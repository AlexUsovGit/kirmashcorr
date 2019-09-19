package com.example.sweater.controller.basedictionarycontroller;

import com.example.sweater.domain.User;
import com.example.sweater.domain.basedictionary.ProductName;
import com.example.sweater.repos.UserRepo;
import com.example.sweater.repos.basedictionaryrepos.ProductNameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class ProductNameController {
    @Autowired
    private ProductNameRepo productNameRepo;
    @Autowired
    private UserRepo userRepo;
    User currentUser;

    @GetMapping("/productname")

    public String productname(Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.put("names", name);
        currentUser= userRepo.findByUsername(name);
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        Iterable<ProductName> productNames = productNameRepo.findAllByOrderByLabelAsc();
        model.put("productNames",productNames);

        return "productname";
    }

    @PostMapping("/productname")
    public String add( String label, Map<String, Object> model)  {
        ProductName productName = new ProductName(label,"autor","active",new Date(System.currentTimeMillis()));
        productNameRepo.save(productName);

        Iterable<ProductName> productNames = productNameRepo.findAllByOrderByLabelAsc();
        model.put("productNames",productNames);

        return "productname";
    }


    @PostMapping("filterproductname")
    public String filter (@RequestParam String filter, Map<String, Object> model){
        Iterable<ProductName> productNames;

        if(filter!=null && !filter.isEmpty()){
            productNames = productNameRepo.findByLabel(filter);
        }else {
            productNames = productNameRepo.findAll();
        }
        model.put("productNames", productNames);
        return "productname";
    }

    @PostMapping("deleteproductname")
    public String delete (@RequestParam String filter, Map<String, Object> model){
        Iterable<ProductName> productNames;

        if(filter!=null && !filter.isEmpty()){
            productNameRepo.deleteById(Long.valueOf(filter));
        }else {

        }
        productNames = productNameRepo.findAll();
        model.put("productNames", productNames);
        return "productname";
    }

}