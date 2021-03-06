package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.entities.validation_groups.UserValidationGroup;
import com.lanit.lkz_project.service.application_service.RegistrationPageService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import com.lanit.lkz_project.service.jpa_entities_service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class RegistrationPageController {

    @Autowired
    RegistrationPageService registrationPageService;
    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;


    @Value("${login_page}")
    private String login_page;
    @Value("${registration_page}")
    private String registration_page;


    @GetMapping("/registration")
    public ModelAndView toRegistrationPage(ModelAndView modelAndView,
                                           @ModelAttribute User user) {
        List<Organization> organizations = organizationService.organizations();
        modelAndView.addObject("organizations", organizations);
        modelAndView.setViewName(registration_page);
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView add(@Validated(value = UserValidationGroup.class) @ModelAttribute User user,
                            BindingResult bindingResult,
                            ModelAndView modelAndView) {
        User userToCheck = userService.findByUsername(user.getUsername());
        if (userToCheck != null) {
            FieldError usernameError = new FieldError("error.user", "username", "Логин должен быть уникальным");
            bindingResult.addError(usernameError);
        }
        if (bindingResult.hasErrors()) {
            log.info("passed wrong arguments: " + bindingResult);
            List<Organization> organizations = organizationService.organizations();
            modelAndView.addObject("organizations", organizations);
            modelAndView.setViewName(registration_page);
            return modelAndView;
        } else {
            registrationPageService.registerUser(user);
            log.info("user with id: " + user.getId() + " registered");
            modelAndView.setViewName(login_page);
            return modelAndView;
        }
    }


}