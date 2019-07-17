package com.lanit.lkz_project.service.application_service;

import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.Role;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.entities_service.OrganizationService;
import com.lanit.lkz_project.service.entities_service.UserService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegistrationPageService {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    public User RegisterUser(@NotBlank String firstName,
                             @NotBlank String lastName,
                             @NotBlank String login,
                             @NotBlank String password,
                             @NotBlank String orgId) {
        Organization organization = organizationService.getOrganization(Long.valueOf(orgId));
        Date registrationDate = new Date();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        user.setOrganization(organization);
        user.setRegistrationDate(registrationDate);
        user.setRole(defineUserRole(organization));
        userService.addUser(user);
        return user;
    }

    private Role defineUserRole(Organization organization) {
        if (organization.isGovernment()) {
            return Role.AUTHORITY;
        } else {
            return Role.EMPLOYEE;
        }
    }
}
