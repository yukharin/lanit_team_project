package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.entity.authorization.UserAccount;
import com.lanit.satonin18.app.service.authorization.UserAccountService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.security.Principal;

@Controller("inputController")
//@RequestMapping("/")
public class InputController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;

//    @GetMapping("/registration")
//    public String registration() {
//
//        return "redirect:/cabinet/notifications";
//    }

    @GetMapping("/")
    public String redirectOnMainPage() {
        return "redirect:/cabinet/notifications";
    }

//    @GetMapping("/login")
//    public ModelAndView toLoginPage(
//            ModelAndView modelAndView, Model model,
//            HttpServletRequest request, HttpServletResponse response) {
//
//        model.addAttribute("error", true);
//        return new ModelAndView("input");
//    }

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
