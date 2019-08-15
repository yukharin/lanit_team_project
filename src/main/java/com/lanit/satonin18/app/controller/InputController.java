package com.lanit.satonin18.app.controller;

import com.lanit.satonin18.app.service.authorization.AccountService;
import com.lanit.satonin18.app.service.entities_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller("inputController")
//@RequestMapping("/")
public class InputController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

//    @GetMapping("/")
//    public String input(
//            Model model){
//        model.addAttribute("user_list", userService.findAll());
//        return "index";
//    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
//        model.addAttribute("message", "You are logged in as " + principal.getName());
//        return "index";
        int idSelectUser =
                accountService.findByUsername(principal.getName())
                        .getId(); //id = id_user
        return "redirect:/cabinet/selectUser?idSelectUser=" + idSelectUser;
    }
//    @PostMapping("/selectUser")
//    public String selectUser(
//            @RequestParam int idSelectUser){
//        return "redirect:/cabinet/selectUser?idSelectUser=" + idSelectUser;
//    }

    //todo edit session, rest - post
    @GetMapping("/output")
    public String output(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
