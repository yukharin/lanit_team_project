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

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    //@Resource(name="organizationService")
    //@Autowired
    //private OrganizationService organizationService;
    private OrganizationService organizationService = new OrganizationServiceImp();

    @GetMapping("/list")
    public String listOrganizations(Model model) {
        List<Organization> organizations = organizationService.organizations();
        model.addAttribute("listOfOrganizations", organizations);
        return "list-organizations";
    }


/*
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("organizationJSP", new Organization());
        modelAndView.setViewName("listOfOrganizations");
        return modelAndView;
    }
*/

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String viewHome(){
        return "home";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("organizationJSP", new Organization("orgName", true));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/check-organization")
    public ModelAndView checkOrganization(@ModelAttribute("organizationJSP") Organization organization) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("secondPage");
        modelAndView.addObject("organizationJSP", organization);
        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }
}