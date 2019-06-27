package com.lanit.lkz_project.controllers;

import com.lanit.lkz_project.entities.Action;
import com.lanit.lkz_project.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/actions")
public class ActionController {

    @Autowired
    private ActionService service;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Action> actions() {
        return service.actions();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Action get(@PathVariable("id") Long id) {
        return service.getAction(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Action action) {
        service.addAction(action);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Action action) {
        if (service.getAction(id) != null) {
            service.updateAction(action);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.removeAction(id);
    }
}
