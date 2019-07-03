package com.lanit.lkz_project.controllers.default_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ActionController {

    @RequestMapping("/actions")
    public String list() {
        return "loginPage";
    }

}
