package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.application_service.RegistrationPageService;
import com.lanit.lkz_project.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationPageController {

    @Autowired
    RegistrationPageService registrationPageService;
    @Autowired
    UserService userService;

    @PostMapping("/registerUser/")
    public String add(@RequestBody User user) {
        registrationPageService.registerUser(user);
        return "loginPage";
    }

    @GetMapping("/reg/")
    public String test(@RequestBody User user) {
        System.err.println("user!: " + user);
        return "loginPage";
    }
}