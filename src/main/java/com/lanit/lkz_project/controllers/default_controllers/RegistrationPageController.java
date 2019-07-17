package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.service.application_service.RegistrationPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationPageController {



    @Autowired
    RegistrationPageService registrationPageService;

    @PostMapping("registration/registerUser/")
    public String registerUser(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String login,
                               @RequestParam String password,
                               @RequestParam String orgId) {
        registrationPageService.RegisterUser(firstName, lastName, login, password, orgId);

        return "loginPage";
    }

}
