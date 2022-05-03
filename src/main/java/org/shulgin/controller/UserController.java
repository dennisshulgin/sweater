package org.shulgin.controller;

import org.shulgin.entity.Role;
import org.shulgin.entity.User;
import org.shulgin.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Map<String, Object> model) {
        List<User> users = userRepo.findAll();
        model.put("users", users);
        return "userList";
    }

    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Map<String, Object> model) {
        model.put("user", user);
        model.put("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String editUser(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("id") User user
    ) {
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.setUsername(username);
        user.getRoles().clear();

        for(String key : form.keySet()) {
            if(roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
        return "redirect:/user";
    }
}
