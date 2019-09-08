package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.data_transfer_objects.TimeFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/timeFilters")
public class TimeFilterController {


    @GetMapping
    public Set<TimeFilter> timeFilters() {
        return TimeFilter.timeFilters();
    }

}
