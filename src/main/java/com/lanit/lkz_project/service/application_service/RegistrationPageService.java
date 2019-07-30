package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.jpa_entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationPageService {


    @Autowired
    private UserService userService;

    @Transactional
    public User registerUser(User user) {
        userService.addUser(user);
        return user;
    }

}
