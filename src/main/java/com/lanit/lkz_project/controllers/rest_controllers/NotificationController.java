//package com.lanit.lkz_project.controllers.rest_controllers;
//
//import com.lanit.lkz_project.entities.Notification;
//import com.lanit.lkz_project.service.NotificationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "/notifications")
//public class NotificationController {
//
//    @Autowired
//    private NotificationService service;
//
//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public List<Notification> notifications() {
//        return service.notifications();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Notification get(@PathVariable("id") Long id) {
//        return service.getNotification(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public void add(@RequestBody Notification notification) {
//        service.addNotification(notification);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable("id") Long id, @RequestBody Notification notification) {
//        if (service.getNotification(id) != null) {
//            service.updateNotification(notification);
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable("id") int id) {
//        service.removeNotification(id);
//    }
//}
