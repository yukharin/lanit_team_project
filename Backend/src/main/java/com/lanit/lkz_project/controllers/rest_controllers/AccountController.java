package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.repositories.entitity_repositories.UserRepository;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonalAccountService personalAccountService;

    @GetMapping
    public PersonalAccountPageDto<Notification> getPage(@AuthenticationPrincipal User user) {
        PersonalAccountPageDto<Notification> page = new PersonalAccountPageDto<>();
        personalAccountService.setAccountPageState(page, user);
        return page;
    }

    @PostMapping
    public PersonalAccountPageDto<Notification> getPage(
             @RequestBody PersonalAccountPageDto<Notification> page,
            @AuthenticationPrincipal User user) {
        personalAccountService.setAccountPageState(page, user);
        return page;
    }

    @GetMapping("/principal")
    public User getUser(@AuthenticationPrincipal User user) {
        return user;
    }

}
