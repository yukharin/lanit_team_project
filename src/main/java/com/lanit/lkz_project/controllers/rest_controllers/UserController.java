//package com.lanit.lkz_project.controllers.rest_controllers;
//
//import com.lanit.lkz_project.entities.User;
//import com.lanit.lkz_project.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "/users")
//public class UserController {
//
//    @Autowired
//    private UserService service;
//
//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public List<User> users() {
//        return service.users();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public User get(@PathVariable("id") Long id) {
//        return service.getUser(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public void add(@RequestBody User user) {
//        service.addUser(user);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable("id") Long id, @RequestBody User user) {
//        if (service.getUser(id) != null) {
//            service.updateUser(user);
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable("id") Long id) {
//        service.removeUser(id);
//    }
//
//}
