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

    public String productnameGet(Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.put("names", name);
        currentUser = userRepo.findFirstByUsername(name);
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        Iterable<ProductName> productNames = productNameRepo.findAllByOrderByLabelAsc();
        model.put("productNames", productNames);

        return "productname";
    }

    @PostMapping("/productname")

    public String productnamePost(Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.put("names", name);
        currentUser = userRepo.findFirstByUsername(name);
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        Iterable<ProductName> productNames = productNameRepo.findAllByOrderByLabelAsc();
        model.put("productNames", productNames);

        return "productname";
    }



    @PostMapping("/productnameAdd")
    public String addProductName(String label, Map<String, Object> model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.put("names", name);
        currentUser = userRepo.findFirstByUsername(name);
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        ProductName productName = new ProductName(label, currentUser.getUsername(), "active", new Date(System.currentTimeMillis()));
        productNameRepo.save(productName);

        Iterable<ProductName> productNames = productNameRepo.findAllByOrderByLabelAsc();
        model.put("productNames", productNames);

        return "productname";
    }

    @PostMapping("deleteproductname")
    public String deleteProductName(@RequestParam String id, Map<String, Object> model) {
        Iterable<ProductName> productNames;

        if (id != null && !id.isEmpty()) {
            productNameRepo.deleteById(Long.valueOf(id));
        }
        productNames = productNameRepo.findAll();
        model.put("productNames", productNames);
        return "productname";
    }


    @PostMapping("editproductname")
    public String editProductName(@RequestParam String id, Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.put("names", name);
        currentUser = userRepo.findFirstByUsername(name);
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        Iterable<ProductName> productNames;
        ProductName productName;

        if (id != null && !id.isEmpty()) {
            productName = productNameRepo.findAllById(Long.valueOf(id));
            if (productName == null) {
                productNames = productNameRepo.findAll();
                model.put("productNames", productNames);
                return "productname";

            } else {
                model.put("currentProductNameId", productName.getId());
                model.put("currentProductNameLabel", productName.getLabel());
                return "admin/productnameedit";
            }

        } else {
            productNames = productNameRepo.findAll();
            model.put("productNames", productNames);
            return "productname";
        }

    }

    @PostMapping("saveproductname")
    public String saveProductName(@RequestParam String id, @RequestParam String label, Map<String, Object> model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.put("names", name);
        currentUser = userRepo.findFirstByUsername(name);
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());

        Iterable<ProductName> productNames;
        ProductName productName;

        if (id != null && !id.isEmpty()) {
            productName = productNameRepo.findAllById(Long.valueOf(id));
            if (productName == null) {
                productNames = productNameRepo.findAll();
                model.put("productNames", productNames);
                return "productname";

            } else {
                productName.setLabel(label);
                productName.setAuthor(currentUser.getUsername());
                productName.setIsActive("active");
                productName.setLastUpdate(new Date(System.currentTimeMillis()));
                productNameRepo.save(productName);
                productNames = productNameRepo.findAll();
                model.put("productNames", productNames);
                return "productname";
            }

        } else {
            productNames = productNameRepo.findAll();
            model.put("productNames", productNames);
            return "productname";
        }

    }
}