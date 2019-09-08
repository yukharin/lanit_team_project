package com.lanit.lkz_project.controllers.mvc_controllers;

import com.lanit.lkz_project.entities.jpa_entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class HomeController {

    @Value("${login_page}")
    private String login_page;


    @RequestMapping("/")
    public String toIndex()
    {
        return "forward:/index.html";
    }


    @GetMapping("/login")
    public ModelAndView toLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName(login_page);
        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            cookies[i].setMaxAge(0);
            response.addCookie(cookies[i]);
        }
        authentication.setAuthenticated(false);
        User user = (User) authentication.getPrincipal();
        log.info("user with id: " + user.getId() + "has been logged out");
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}