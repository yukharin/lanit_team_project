package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.jpa_entities.Notification;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import com.lanit.lkz_project.service.jpa_entities_service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private PersonalAccountService personalAccountService;

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
    public void create(@AuthenticationPrincipal User user,@RequestBody Notification notification) {
        personalAccountService.addNotification(notification, user);
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
