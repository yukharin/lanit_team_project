package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.service.OrganizationService;
import com.lanit.lkz_project.service.RoleService;
import com.lanit.lkz_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @RequestMapping("/")
    public String toLoginPage() {
        return "loginPage";
    }

    @GetMapping("registration/")
    public String toRegistrationPage(Model model) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("organizations", organizations);
        return "userRegistrationPage";
    }


}
