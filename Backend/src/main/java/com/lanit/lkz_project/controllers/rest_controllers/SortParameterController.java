package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.data_transfer_objects.SortParameter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin
@RestController
public class SortParameterController {

    @GetMapping("/sortParameters")
    public Set<SortParameter> sortParameters() {
        return SortParameter.sortParameters();
    }
}
