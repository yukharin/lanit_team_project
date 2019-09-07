package com.lanit.lkz_project.controllers.rest_controllers;

import com.lanit.lkz_project.entities.jpa_entities.Organization;
import com.lanit.lkz_project.service.jpa_entities_service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account/organizations")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @GetMapping
    public List<Organization> getOrganizations() {
        return organizationService.organizations();
    }

}
