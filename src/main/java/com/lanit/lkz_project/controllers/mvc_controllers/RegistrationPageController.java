package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.application_service.RegistrationPageService;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import com.lanit.lkz_project.service.jpa_entities_service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
public class RegistrationPageController {

    @Autowired
    RegistrationPageService registrationPageService;
    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;

    private static Logger logger = LoggerFactory.getLogger(PersonalAccountController.class);

    @PostMapping("/registerUser/")
    public String add(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("user passed this registration parameters: " + user
                    + "and validation mechanism found this errors: " + bindingResult);
            List<Organization> organizations = organizationService.organizations();
            model.addAttribute("organizations", organizations);
            logger.info("added list of organizations to the model: " + organizations);
            return "userRegistrationPage";
        } else {
            registrationPageService.registerUser(user);
            logger.info("user with this passed parameters: " + user
                    + "successfully finished registration process, then sending him him loginPage.html");
            return "loginPage";
        }
    }


}
