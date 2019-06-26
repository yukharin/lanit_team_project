package com.lanit.satonin18.controller.crud;

import com.lanit.satonin18.model.Notification;
import com.lanit.satonin18.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("notificationController")
//@Scope("session")
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private CrudService<Notification> notificationService;

    @GetMapping("/")
    public String index(Model model) {
        return "crud/notification/index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", notificationService.list());
        return "crud/notification/list";
    }
    
}