package com.example.crud.controller;

import com.example.crud.model.RoleName;
import com.example.crud.model.User;
import com.example.crud.service.UserService;
import com.example.crud.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ModelAndView showUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("person");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
    @GetMapping("/hello")
    public String printWelcome(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getCustomerList());
        model.addAttribute("new_user", new User());
        model.addAttribute("roleName", new RoleName());
        return "welcome";
    }

}
