package com.example.sweater.controller;

import com.example.sweater.domain.TableNames;
import com.example.sweater.domain.User;
import com.example.sweater.repos.TableNamesRepo;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TablesNameController {

    @Autowired
    private TableNamesRepo tableNamesRepo;

    @GetMapping("/getTables")
    public String getTables(Map<String, Object> model) {
    Iterable<TableNames> tablesList = tableNamesRepo.findAll();

        model.put("tablesList", tablesList);


        return "tableslist";
    }


}