package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.service.application_service.RegistrationPageService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationPageController {



    @Autowired
    RegistrationPageService registrationPageService;

    @PostMapping("registration/registerUser/")
    public String registerUser(@NonNull @RequestParam String firstName,
                               @NonNull @RequestParam String lastName,
                               @NonNull @RequestParam String login,
                               @NonNull @RequestParam String password,
                               @NonNull @RequestParam String orgId) {
        registrationPageService.RegisterUser(firstName, lastName, login, password, orgId);
        return "loginPage";
    }

}
