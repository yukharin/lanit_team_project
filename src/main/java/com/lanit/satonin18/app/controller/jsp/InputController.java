package com.lanit.satonin18.app.controller.jsp;

import com.lanit.satonin18.app.service.entities_service.authorization.UserAccountService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("inputController")
//@RequestMapping("/")
public class InputController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;


    @GetMapping("/")
    public String redirectOnMainPage() {
        return "redirect:/cabinet/notifications";
    }

    @GetMapping("/login")
    public ModelAndView toLoginPage(
            ModelAndView modelAndView, Model model,
            HttpServletRequest request, HttpServletResponse response) {

        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("messageRegistrationSuccess", request.getParameter("messageRegistrationSuccess"));

        return new ModelAndView("inputForm");
    }

    @GetMapping("/output")
    public String output(Authentication authentication,
                         HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName()
                    .equals("cookie-me")) {
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
                break;
            }
        }
        authentication.setAuthenticated(false);
        return "redirect:/"; //redirectOnMainPage
    }
}
