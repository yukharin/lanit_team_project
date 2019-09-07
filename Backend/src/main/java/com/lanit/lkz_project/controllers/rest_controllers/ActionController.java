package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.jpa_entities.Action;
import com.lanit.lkz_project.service.jpa_entities_service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/account/notifications/{id}/actions")
public class ActionController {


    @Autowired
    private ActionService actionService;

    @GetMapping
    public List<Action> getActionsOfNotification(@PathVariable long id) {
        return actionService.getActionsOfNotification(id);
    }


}
