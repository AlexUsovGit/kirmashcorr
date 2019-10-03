package com.example.sweater.service;

import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthenticationInfo {
    @Autowired
    private UserRepo userRepo;

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByUsername(auth.getName());
        return currentUser;
    }

    public Map<String, Object> getPermission(Map<String, Object> model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepo.findByUsername(auth.getName());
        model.put("currentUser", currentUser);
        model.put("currentRole", currentUser.getRoles().toString());
        model.put("showAdmin", currentUser.isShowAdmin());
        model.put("showSklad", currentUser.isShowSklad());
        model.put("showReport", currentUser.isShowReport());
        model.put("showStore", currentUser.isShowStore());
        return model;
    }


    public Map<String, Object> getDepartmentList(Map<String, Object> model) {

        List<String> departmentList = userRepo.findAllDepartmentOrderByNameAsc();
        departmentList.add(0," - Отобразить все - ");
        model.put("departmentList", departmentList);
        return model;
    }
    public Map<String, Object> getDepartmentListV(Map<String, Object> model) {

        List<String> departmentList = userRepo.findAllDepartmentOrderByNameAsc();
        model.put("departmentList", departmentList);
        return model;
    }
}
