package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.service.jpa_entities_service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account/notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping
    public List<Notification> getAll() {
        return notificationService.notifications();
    }

    @GetMapping("/{id}")
    public Notification get(@PathVariable long id) {
        return notificationService.getNotification(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(Notification notification) {
        notificationService.addNotification(notification);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(Notification notification) {
        notificationService.updateNotification(notification);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        notificationService.removeNotification(id);
    }


}
