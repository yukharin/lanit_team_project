package com.lanit.satonin18.controller;

import com.lanit.satonin18.model.entity.Organization;
import com.lanit.satonin18.model.service.OrganizationService;
import com.lanit.satonin18.model.service.OrganizationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String viewHome(){
        return "home";
    }

/*
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/list")
    public String listOrganizations(Model model) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("listOfOrganizations", organizations);
        return "list-organizations";
    }
*/

/*
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("organizationJSP", new Organization());
        modelAndView.setViewName("listOfOrganizations");
        return modelAndView;
    }
*/
}