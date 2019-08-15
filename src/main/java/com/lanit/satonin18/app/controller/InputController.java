package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.service.authorization.UserAccountService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    //todo edit session, rest - post
    @GetMapping("/logout")
    public String output(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
