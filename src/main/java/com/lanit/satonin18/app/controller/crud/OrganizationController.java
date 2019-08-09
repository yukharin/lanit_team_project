package com.lanit.satonin18.app.controller.crud;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("organizationController")
@RequestMapping("/crud/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", organizationService.findAll());
        return "crud/organization/list";
    }

    @PostMapping("/search")
    public String searchUsers(@RequestParam("theSearchName") String theSearchName,
                              Model theModel) {
        System.out.println(theSearchName);
        List<Organization> theOrganizations = organizationService.findByNameIgnoreCaseContaining(theSearchName);

        theModel.addAttribute("list", theOrganizations);
        theModel.addAttribute("valueSearch", theSearchName);
        return "crud/organization/list";
    }
}