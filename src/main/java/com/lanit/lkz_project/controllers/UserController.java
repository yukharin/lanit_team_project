package com.lanit.lkz_project.controllers;

import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/users")
    @ResponseBody
    public List<User> users() {
        return userService.users();
    }

}
