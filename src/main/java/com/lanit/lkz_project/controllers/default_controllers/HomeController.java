package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.OrganizationService;
import com.lanit.lkz_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    UserService userService;

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

    @PostMapping("registerUser/")
    public String registerUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastNAme");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Organization organization = organizationService.getOrganization(Long.valueOf(request.getParameter("orgId")));
        Date registrationDate = new Date();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        user.setOrganization(organization);
        user.setRegistrationDate(registrationDate);
        userService.addUser(user);
        return "loginPage";
    }

}
