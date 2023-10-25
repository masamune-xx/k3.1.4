package com.xpl.k314.controllers;

import com.xpl.k314.models.User;
import com.xpl.k314.services.RoleService;
import com.xpl.k314.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @GetMapping({"/", ""})
    public String userList(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.getUserList());
        model.addAttribute("roles", roleService.getRoleList());
        return "admin";
    }

    @PostMapping("/{id}")
    public String userSave(@ModelAttribute("user") User user,
                           @RequestParam("roleIds") List<Integer> roleIds) {
        user.setRoles(roleIds
                .stream()
                .map(roleService::getRoleById)
                .collect(Collectors.toSet()));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String userDelete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
