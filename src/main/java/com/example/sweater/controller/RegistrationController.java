package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, String role, String storeName , Map<String,Object> model ){
     /*
      User userFromDb =   userRepo.findByUsername(user.getUsername());
      if(userFromDb != null){
          model.put("message","Пользователь с таким логином существует.");
          return "registration";
      }

      */
        user.setActive(true);
        user.setStoreName(storeName);

      if(role.equals("ADMIN")){
          user.setRoles(Collections.singleton(Role.ADMIN));
      }else{
          user.setRoles(Collections.singleton(Role.USER));
      }

        userRepo.save(user);

        return "redirect:/login";
        //return "registration";
    }
}
