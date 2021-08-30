package com.example.crud.controller;

import com.example.crud.model.RoleName;
import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listCustomers(Model model) {
        model.addAttribute("users", userService.getCustomerList());
        return "list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.showById(id));
        return "user";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.showById(id));
        return "edit";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/hello";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
//        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/create")
    public String saveCustomer(@ModelAttribute("user") User user, @ModelAttribute("roleName")RoleName roleName) {
        userService.save(user, roleName);
        return "redirect:/hello";
    }
}
