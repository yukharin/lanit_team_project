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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.lanit.lkz_project.controllers.Utils.LOGIN_PAGE;
import static com.lanit.lkz_project.controllers.Utils.REGISTRATION_PAGE;

@Controller
public class HomePageController {

    private static Logger logger = LoggerFactory.getLogger(PersonalAccountController.class);


    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserAuthorizationService userServiceAuthorization;


    @RequestMapping(path = "/", produces = "text/html; charset=UTF-8")
    public ModelAndView toLoginPage(ModelAndView modelAndView) {
        logger.trace("sending loginPage.html");
        modelAndView.setViewName(LOGIN_PAGE);
        return modelAndView;
    }

    @GetMapping("/registration/")
    public ModelAndView toRegistrationPage(ModelAndView modelAndView,
                                           @ModelAttribute User user) {
        List<Organization> organizations = organizationService.organizations();
        modelAndView.addObject("organizations", organizations);
        modelAndView.setViewName(REGISTRATION_PAGE);
        logger.trace("Adding model attribute - list of all organizations, then sending userRegistrationPage.html");
        return modelAndView;
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
