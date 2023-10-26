package com.xpl.k314.controllers;

import com.xpl.k314.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public String userInfo(Principal principal, Model model) {
        model.addAttribute("principal", userService.getUserByEmail(principal.getName()));
        return "user";
    }
}
