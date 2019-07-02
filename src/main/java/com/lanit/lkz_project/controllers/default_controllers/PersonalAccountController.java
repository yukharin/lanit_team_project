package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.Notification;
import com.lanit.lkz_project.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PersonalAccountController {

    @Autowired
    private NotificationService service;


    @GetMapping("/account")
    public String getAllNotifications(Model model) {
        List<Notification> notifications = service.notifications();
        model.addAttribute("notifications", notifications);
        return "index";
    }

}