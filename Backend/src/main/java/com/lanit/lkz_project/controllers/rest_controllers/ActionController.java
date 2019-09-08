package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.jpa_entities.Action;
import com.lanit.lkz_project.entities.jpa_entities.User;
import com.lanit.lkz_project.service.application_service.PersonalAccountService;
import com.lanit.lkz_project.service.jpa_entities_service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications/{id}/actions")
public class ActionController {


    @Autowired
    private ActionService actionService;

    @Autowired
    private PersonalAccountService personalAccountService;

    @GetMapping
    public List<Action> getActionsOfNotification(@PathVariable long id) {
        return actionService.getActionsOfNotification(id);
    }

    @PostMapping
    public void addAction(@RequestBody Action action,
                          @AuthenticationPrincipal User user) {
        personalAccountService.addAction(user, action);
    }


}
