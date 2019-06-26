package com.lanit.satonin18.controller.crud;


import com.lanit.satonin18.model.Organization;
import com.lanit.satonin18.service.CrudService;
import com.lanit.satonin18.service.no_use.OrganizationService;
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


/*
    @ModelAttribute("organization")
    public Organization formBackingObject() {
        return new Organization();
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrganization(@ModelAttribute("organization") @Valid Organization organization, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("list", organizationService.list());
            return "editOrganizations";
        }

        organizationService.update(organization);
        return "redirect:/";
    }
*/

}