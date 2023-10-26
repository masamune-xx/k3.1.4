package com.xpl.k314.controllers;

import com.xpl.k314.models.User;
import com.xpl.k314.services.RoleService;
import com.xpl.k314.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @GetMapping({"/", ""})
    public String userList(Principal principal, Model model) {
        model.addAttribute("principal", userService.getUserByEmail(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.getUserList());
        model.addAttribute("roles", roleService.getRoleList());
        return "admin";
    }

    @PutMapping("/{id}")
    public String userEdit(@ModelAttribute("user") User user,
                           @RequestParam("roleIds") List<Integer> roleIds) {
        userService.saveUserWithRoles(user, roleIds);
        return "redirect:/admin";
    }

    @PostMapping({"/", ""})
    public String userCreate(@ModelAttribute("user") User user,
                             @RequestParam("roleIds") List<Integer> roleIds) {
        userService.saveUserWithRoles(user, roleIds);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String userDelete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
