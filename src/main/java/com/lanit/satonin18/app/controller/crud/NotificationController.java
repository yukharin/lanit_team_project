package com.lanit.satonin18.app.controller.crud;

import com.lanit.satonin18.app.entity.Notification;
import com.lanit.satonin18.app.service.entities_service.CrudService;
import com.lanit.satonin18.app.service.entities_service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("notificationController")
@RequestMapping("/crud/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", notificationService.list());
        return "crud/notification/list";
    }

}