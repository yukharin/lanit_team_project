package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.OrganizationService;
import com.lanit.lkz_project.service.RoleService;
import com.lanit.lkz_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class RegistrationPageController {


    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("registration/registerUser/")
    public String registerUser(@RequestParam(name = "firstName") String firstName,
                               @RequestParam(name = "lastName") String lastName,
                               @RequestParam(name = "login") String login,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "orgId") String orgId) {
        Organization organization = organizationService.getOrganization(Long.valueOf(orgId));
        Date registrationDate = new Date();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        user.setOrganization(organization);
        user.setRegistrationDate(registrationDate);
        user.setRole(roleService.getRole(1L));
        userService.addUser(user);
        return "loginPage";
    }

}
