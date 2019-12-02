package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.domain.basedictionary.ProductName;
import com.example.sweater.dto.InfoDTO;
import com.example.sweater.repos.InfoDTORepo;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
public class ExchangeController {

    @Autowired
    private UserRepo userRepo;
    User currentUser;
    @Autowired
    private InfoDTORepo infoDTORepo;


    @PostMapping("/addAllInfo")
    public String reportPage(@RequestBody InfoDTO infoDTO) {

            infoDTORepo.save(infoDTO);

        return "Ok";
    }
    @GetMapping("/all-info")
    public String getAllInfo (Map<String, Object> model) {

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


        Iterable<InfoDTO> infoDTOS = infoDTORepo.findAll();
        model.put("infoDTOS", infoDTOS);
        return "allinfo";
    }


}
