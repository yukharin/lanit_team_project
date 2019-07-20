package com.lanit.satonin18.mvc.controller;

import com.lanit.satonin18.Pagination;
import com.lanit.satonin18.mvc.entity.Notification;
import com.lanit.satonin18.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("inputController")
//@RequestMapping("/")
public class InputController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String input(
            Model model){
        model.addAttribute("user_list", userService.list());
        return "input/index";
    }

    @PostMapping("/selectUser")
    public String selectUser(@RequestParam("idSelectUser") int idSelectUser,

                             Model model) throws NoSuchFieldException {
        //currentUser = userService.getById(idSelectUser);

        return "redirect:/cabinet/selectUser?idSelectUser=" + idSelectUser;
    }
}
