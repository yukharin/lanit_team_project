package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.authorization.UserAuthorizationService;
import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomePageController {

    private static Logger logger = LoggerFactory.getLogger(PersonalAccountController.class);

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserAuthorizationService userServiceAuthorization;


    @RequestMapping("/")
    public String toLoginPage() {
        logger.trace("sending loginPage.html");
        return "loginPage";
    }

    @GetMapping("/registration/")
    public String toRegistrationPage(Model model,
                                     @ModelAttribute User user) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("organizations", organizations);
        logger.trace("Adding model attribute - list of all organizations, then sending userRegistrationPage.html");
        return "userRegistrationPage";
    }


    @PostMapping("/login/")
    public String login(@NonNull @RequestParam String login,
                        @NonNull @RequestParam String password,
                        HttpSession session) {
        @NonNull User user = userServiceAuthorization.authorize(login, password);
        session.setAttribute("login", login);
        session.setAttribute("password", password);
        logger.info("User: " + user + " authorized, sending account.html");
        return "redirect:/account/";
    }

    @GetMapping("/logout/")
    public String logout(@NonNull @SessionAttribute String login,
                         @NonNull @SessionAttribute String password,
                         HttpSession session) {
        @NonNull User user = userServiceAuthorization.authorize(login, password);
        session.removeAttribute("login");
        session.removeAttribute("password");
        logger.info("User: " + user + " made a logout, redirecting to main page");
        return "redirect:/";
    }


}
