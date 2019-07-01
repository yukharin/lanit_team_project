package com.lanit.satonin18.controller;

import com.lanit.satonin18.model.Notification;
import com.lanit.satonin18.model.User;
import com.lanit.satonin18.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("cabinetController")
@RequestMapping("/cabinet")
public class CabinetController {

    @Autowired
    private CrudService<Notification> notificationService;
    @Autowired
    private CrudService<User> userService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("notific_list", notificationService.list());
        model.addAttribute("user_list", userService.list());//by -> ORG -> FILTER SEARCH
        return "cabinet/list";
    }
}