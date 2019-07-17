package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.authorization.UserServiceAuthorization;
import com.lanit.lkz_project.entities.Organization;
import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.entities_service.OrganizationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserServiceAuthorization userServiceAuthorization;


    @RequestMapping("/")
    public String toLoginPage() {
        return "loginPage";
    }

    @GetMapping("/registration/")
    public String toRegistrationPage(Model model) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("organizations", organizations);
        return "userRegistrationPage";
    }


    @PostMapping("/login/")
    public String login(@NonNull @RequestParam String login,
                        @NonNull @RequestParam String password,
                        HttpSession session) {
        @NonNull User user = userServiceAuthorization.authorize(login, password);
        session.setAttribute("login", login);
        session.setAttribute("password", password);
        return "redirect:/account/";
    }

    @GetMapping("/logout/")
    public String logout(HttpSession session) {
        session.removeAttribute("login");
        session.removeAttribute("password");
        return "redirect:/";
    }


}
