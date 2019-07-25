package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.Role;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.entities_service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class RegistrationPageService {


    @Autowired
    private UserService userService;

    @Transactional
    public User registerUser(User user) {
        Organization organization = user.getOrganization();
        user.setOrganization(organization);
        user.setRegistrationDate(new Date());
        user.setRole(defineUserRole(organization));
        userService.addUser(user);
        return user;
    }

    private Role defineUserRole(@NonNull Organization organization) {
        if (organization.isGovernment()) {
            return Role.AUTHORITY;
        } else {
            return Role.EMPLOYEE;
        }
    }
}
