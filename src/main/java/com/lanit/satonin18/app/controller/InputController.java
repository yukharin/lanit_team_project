package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.service.authorization.UserAccountService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller("inputController")
//@RequestMapping("/")
public class InputController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/")
    public String afterLogin(Model model, Principal principal) {
//        model.addAttribute("message", "You are logged in as " + principal.getName());
        int idSelectUser =
                userAccountService.findByUsername(principal.getName())
                        .getId(); //id = id_user
        return "redirect:/cabinet/selectUser?idSelectUser=" + idSelectUser;
    }

//    //todo edit session, rest - post
    @GetMapping("/output")
    public String output(HttpSession session, Authentication authentication){
        authentication.setAuthenticated(false);
//        session.removeAttribute("user");
//        return "redirect:/login";

//        return "q";
        return "input";
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public ModelAndView logout(ModelAndView modelAndView, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//        Cookie[] cookies = request.getCookies();
//        for (int i = 0; i < cookies.length; i++) {
//            cookies[i].setMaxAge(0);
//            response.addCookie(cookies[i]);
//        }
//        authentication.setAuthenticated(false);
//        modelAndView.setViewName("redirect:/");
//        return modelAndView;
//    }
}
