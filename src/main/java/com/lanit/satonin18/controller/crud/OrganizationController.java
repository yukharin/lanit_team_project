package com.lanit.satonin18.controller.crud;

import com.lanit.satonin18.model.Organization;
import com.lanit.satonin18.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("organizationController")
//@Scope("session")
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private CrudService<Organization> organizationService;

    @GetMapping("/")
    public String index(Model model) {
        return "crud/organization/index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", organizationService.list());
        return "crud/organization/list";
    }

}