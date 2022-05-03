package org.shulgin.controller;
import com.fasterxml.jackson.core.io.IOContext;
import org.shulgin.entity.Message;
import org.shulgin.entity.User;
import org.shulgin.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(name = "filter", required = false, defaultValue = "") String filter, @RequestParam(name="name", required=false, defaultValue="World") String name,
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

    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             @RequestParam String tag,
                             @RequestParam("file") MultipartFile file) {
        Message message = new Message();
        Path dirPath = Paths.get(uploadPath);
        if(!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(file != null && !file.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                String filePath = uuid + file.getOriginalFilename();
                File fileNew = new File(uploadPath + "/"+ filePath);
                System.out.println(uploadPath + "/" + filePath);
                try {
                    file.transferTo(fileNew);
                    message.setFilePath(filePath);
                }catch(IOException e) {
                    e.printStackTrace();
                }
        }
        message.setAuthor(user);
        message.setText(text);
        message.setTag(tag);
        messageRepo.save(message);
        return "redirect:/main";
    }
}