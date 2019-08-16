package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.entities.data_transfer_objects.PersonalAccountPageDto;
import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private PersonalAccountService personalAccountService;


    @RequestMapping("/")
    public ModelAndView toAccountPage(ModelAndView modelAndView,
                                      @AuthenticationPrincipal User user,
                                      @ModelAttribute PersonalAccountPageDto<Notification> pageDTO) {
        personalAccountService.setAccountPageState(pageDTO, user);
        modelAndView.addObject("pageDTO", pageDTO);
        modelAndView.setViewName(account_page);
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView toLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName(login_page);
        return modelAndView;
    }


    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }
        authentication.setAuthenticated(false);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
