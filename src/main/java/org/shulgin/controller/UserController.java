package org.shulgin.controller;

import org.shulgin.entity.Role;
import org.shulgin.entity.User;
import org.shulgin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Map<String, Object> model) {
        List<User> users = userService.findAll();
        model.put("users", users);
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String editUser(@PathVariable User user, Map<String, Object> model) {
        model.put("user", user);
        model.put("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String editUser(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("id") User user
    ) {
        userService.editUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(@AuthenticationPrincipal User user,
                              Map<String, Object> model) {
        model.put("username", user.getUsername());
        model.put("email", user.getEmail());
        return "profile";
    }

    @PostMapping("profile")
    public String editProfile(@AuthenticationPrincipal User user,
                              @RequestParam String email,
                              @RequestParam String password) {
        userService.editProfile(user, email, password);
        return "redirect:/user/profile";
    }
}
