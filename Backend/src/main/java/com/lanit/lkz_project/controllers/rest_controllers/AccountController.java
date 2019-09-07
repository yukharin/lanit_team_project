package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.jpa_entities.Action;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.repositories.entitity_repositories.UserRepository;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonalAccountService personalAccountService;

    @PostMapping("/")
    public PersonalAccountPageDto<Notification> getPage(
            @RequestBody PersonalAccountPageDto<Notification> page) {
        User user = userRepository.findByUsername("yukharin");
        personalAccountService.setAccountPageState(page, user);
        return page;
    }

    @GetMapping("/")
    public PersonalAccountPageDto<Notification> getPage() {
        User user = userRepository.findByUsername("yukharin");
        PersonalAccountPageDto<Notification> page = new PersonalAccountPageDto<>();
        personalAccountService.setAccountPageState(page, user);
        return page;
    }

    @GetMapping("/principal")
    public User getUser() {
        User user = userRepository.findByUsername("yukharin");
        return user;
    }


    @PostMapping("/addNotification")
    public void addNotification(@RequestBody Notification notification) {
        User user = userRepository.findByUsername("yukharin");
        personalAccountService.addNotification(notification, user);
    }

    @PostMapping("/addAction")
    public void addAction(@RequestBody Action action) {
        User user = userRepository.findByUsername("yukharin");
        personalAccountService.addAction(user, action);
    }
}
