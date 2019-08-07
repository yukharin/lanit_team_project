package com.lanit.satonin18.app.controller.crud;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.service.entities_service.CrudService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("organizationController")
@RequestMapping("/crud/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", organizationService.list());
        return "crud/organization/list";
    }

}