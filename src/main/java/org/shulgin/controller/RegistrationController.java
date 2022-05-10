package org.shulgin.controller;

import org.shulgin.entity.User;
import org.shulgin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if(!userService.addUser(user)) {
            model.put("message", "User exists");
            return "registration";
        }
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activateCode(@PathVariable("code") String code, Map<String, Object> model) {
        boolean isActivate = userService.activateUser(code);
        System.out.println(isActivate);
        if(isActivate) {
            model.put("message", "User successfully activated");
        } else {
            model.put("message", "Code not found");
        }
        return "login";
    }
}
