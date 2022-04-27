package org.shulgin.controller;
import org.shulgin.entity.Message;
import org.shulgin.entity.User;
import org.shulgin.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             @RequestParam String tag) {
        Message message = new Message();
        message.setAuthor(user);
        message.setText(text);
        message.setTag(tag);
        messageRepo.save(message);
        return "redirect:/main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter,
                         Map<String, Object> model) {
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}