package com.example.sweater.controller;

import com.example.sweater.domain.ColumnNames;
import com.example.sweater.domain.TableNames;
import com.example.sweater.repos.ColumnNamesRepo;
import com.example.sweater.repos.TableNamesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    Iterable<ColumnNames> colList = columnNamesRepo.findAllInfoColumn();
    Iterable<ColumnNames> colListTable = columnNamesRepo.findAllInfoColumnbyTableName("receipt");
//    Iterable<ColumnNames> columnList = columnNamesRepo.findAll();
//        List<ColumnNames> columnNames = columnNamesRepo.findAllInfo();


        model.put("tablesList", tablesList);
        model.put("colList", colList);
        model.put("colListTable", colListTable);
//        model.put("columnNames", columnNames);


        return "tableslist";
    }


}