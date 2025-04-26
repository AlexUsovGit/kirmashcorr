package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {


        return "enter";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        String roles = "";

        for (GrantedAuthority authority : auth.getAuthorities()) {
            roles = authority.getAuthority();
        }
        model.put("names", name);
        model.put("roles", roles);

        User currentUser = userRepo.findFirstByUsername(name);

        model.put("currentUser", currentUser);
        boolean active;

        CsrfToken csrf = (CsrfToken) request.getAttribute("_csrf");

        model.put("currentRole", currentUser.getRoles().toString());
        model.put("currentUserName", currentUser.getUsername());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        model.put("_csrf", csrf);

        return "main";
    }


}