package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomePageController {

    private static Logger logger = LoggerFactory.getLogger(PersonalAccountController.class);

    @Value("${account_page}")
    private String account_page;
    @Value("${registration_page}")
    private String registration_page;
    @Value("${login_page}")
    private String login_page;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private PersonalAccountService personalAccountService;


    @RequestMapping(path = {"/"}, produces = "text/html; charset=UTF-8")
    public ModelAndView toAccountPage(ModelAndView modelAndView,
                                      @AuthenticationPrincipal User user,
                                      @ModelAttribute PersonalAccountPageDto<Notification> pageDTO) {
        logger.trace("sending loginPage.html");
        personalAccountService.setAccountPageState(pageDTO, user);
        modelAndView.addObject("pageDTO", pageDTO);
        modelAndView.addObject("user", user);
        modelAndView.setViewName(account_page);
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView toLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName(login_page);
        return modelAndView;
    }

    @GetMapping("/registration/")
    public ModelAndView toRegistrationPage(ModelAndView modelAndView,
                                           @ModelAttribute User user) {
        List<Organization> organizations = organizationService.organizations();
        modelAndView.addObject("organizations", organizations);
        modelAndView.setViewName(registration_page);
        logger.trace("Adding model attribute - list of all organizations, then sending userRegistrationPage.html");
        return modelAndView;
    }

}
