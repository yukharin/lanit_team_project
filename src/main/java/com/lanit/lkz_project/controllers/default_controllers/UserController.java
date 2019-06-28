package com.lanit.lkz_project.controllers.default_controllers;

import com.lanit.lkz_project.entities.User;
import com.lanit.lkz_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;


//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public String get(@PathVariable("id") Long id, Model model) {
//        User user = service.getUser(id);
//        model.addAttribute(user);
//        return "index";
//    }

    //    @RequestMapping(method = RequestMethod.POST)
//    public void add(@RequestBody User user) {
//        service.addUser(user);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public void update(@PathVariable("id") Long id, User user) {
//        if (service.getUser(id) != null) {
//            service.updateUser(user);
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable("id") Long id) {
//        service.removeUser(id);
//    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        List<User> users = service.users();
        model.addAttribute("users", users);
        return "index";
    }


}
