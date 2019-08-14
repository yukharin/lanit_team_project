package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.enums.Role;
import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import com.lanit.lkz_project.service.jpa_entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationPageService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User registerUser(User user) {
        user.setRegistrationDate(LocalDateTime.now());
        Organization organization = organizationService.getOrganization(user.getOrganization().getId());
        user.setRole(defineRole(organization));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return user;
    }

    private Role defineRole(Organization organization) {
        if (organization.isGovernment()) {
            return Role.AUTHORITY;
        } else {
            return Role.EMPLOYEE;
        }
    }
}


