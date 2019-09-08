package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.enums.ActionType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/actionTypes")
public class ActionTypeController {

    @GetMapping
    public Set<ActionType> getActionTypes() {
        return ActionType.types();
    }

}
