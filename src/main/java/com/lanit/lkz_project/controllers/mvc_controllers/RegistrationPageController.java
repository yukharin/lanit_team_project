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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static com.lanit.lkz_project.controllers.Utils.LOGIN_PAGE;
import static com.lanit.lkz_project.controllers.Utils.REGISTRATION_PAGE;


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
    public ModelAndView add(@Valid @ModelAttribute User user, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            logger.info("user passed this registration parameters: " + user
                    + "and validation mechanism found this errors: " + bindingResult);
            List<Organization> organizations = organizationService.organizations();
            modelAndView.addObject("organizations", organizations);
            modelAndView.setViewName(REGISTRATION_PAGE);
            logger.info("added list of organizations to the model: " + organizations);
            return modelAndView;
        } else {
            registrationPageService.registerUser(user);
            modelAndView.setViewName(LOGIN_PAGE);
            logger.info("user with this passed parameters: " + user
                    + "successfully finished registration process, then sending him him loginPage.html");
            return modelAndView;
        }
    }


}
