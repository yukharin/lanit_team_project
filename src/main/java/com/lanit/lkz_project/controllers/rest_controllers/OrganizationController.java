//package com.lanit.lkz_project.controllers.rest_controllers;
//
//import com.lanit.lkz_project.entities.Organization;
//import com.lanit.lkz_project.service.OrganizationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "/organizations")
//public class OrganizationController {
//
//    @Autowired
//    private OrganizationService service;
//
//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public List<Organization> organizations() {
//        return service.organizations();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Organization get(@PathVariable("id") Long id) {
//        return service.getOrganization(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public void add(@RequestBody Organization organization) {
//        service.addOrganization(organization);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable("id") Long id, @RequestBody Organization organization) {
//        if (service.getOrganization(id) != null) {
//            service.updateOrganization(organization);
//        }
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable("id") Long id) {
//        service.removeOrganization(id);
//    }
//}
