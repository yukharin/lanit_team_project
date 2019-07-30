package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.Role;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.application_service.RegistrationPageService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import com.lanit.lkz_project.service.jpa_entities_service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@Controller
public class RegistrationPageController {

    @Autowired
    RegistrationPageService registrationPageService;
    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;

    @PostMapping("/registerUser/")
    public String add(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.err.println("USER: " + user);
            List<Organization> organizations = organizationService.organizations();
            model.addAttribute("organizations", organizations);
            return "userRegistrationPage";
        } else {
            System.err.println("USER: " + user);
            Organization organization = user.getOrganization();
            user.setOrganization(organization);
            user.setRegistrationDate(new Date());
            user.setRole(defineUserRole(organization));
            registrationPageService.registerUser(user);
            return "loginPage";
        }
    }

    private Role defineUserRole(@NonNull Organization organization) {
        if (organization.isGovernment()) {
            return Role.AUTHORITY;
        } else {
            return Role.EMPLOYEE;
        }
    }

}
