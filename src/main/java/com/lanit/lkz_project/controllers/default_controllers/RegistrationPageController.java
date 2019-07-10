package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.entities_service.OrganizationService;
import com.lanit.lkz_project.service.entities_service.RoleService;
import com.lanit.lkz_project.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
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
    public String registerUser(@NotNull @RequestParam String firstName,
                               @NotNull @RequestParam String lastName,
                               @NotNull @RequestParam String login,
                               @NotNull @RequestParam String password,
                               @NotNull @RequestParam String orgId) {
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
