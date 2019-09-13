package com.example.sweater.controller;

import com.example.sweater.domain.ColumnNames;
import com.example.sweater.domain.ColumnNames2;
import com.example.sweater.domain.TableNames;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ColumnNamesRepo;
import com.example.sweater.repos.TableNamesRepo;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class TablesNameController {

    @Autowired
    private TableNamesRepo tableNamesRepo;

    @Autowired
    private ColumnNamesRepo columnNamesRepo;

    @GetMapping("/getTables")
    public String getTables(Map<String, Object> model) {
    Iterable<TableNames> tablesList = tableNamesRepo.findAllInfo();
    Iterable<ColumnNames2> colList = columnNamesRepo.findAllInfoColumn();
    Iterable<ColumnNames2> colListTable = columnNamesRepo.findAllInfoColumnbyTableName("receipt");
//    Iterable<ColumnNames> columnList = columnNamesRepo.findAll();
//        List<ColumnNames> columnNames = columnNamesRepo.findAllInfo();


        model.put("tablesList", tablesList);
        model.put("colList", colList);
        model.put("colListTable", colListTable);
//        model.put("columnNames", columnNames);


        return "tableslist";
    }


}