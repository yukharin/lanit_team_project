package com.lanit.satonin18.controller.crud;


import com.lanit.satonin18.service.OrganizationService;
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
    private OrganizationService organizationService;
    //private OrganizationService organizationService = new OrganizationServiceImp();

    @GetMapping("/")
    public String index(Model model) {
        return "crud/user/index";
    }
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", organizationService.list());
        return "crud/organization/list";
    }
/*
    @GetMapping("/")
    public String organizationForm(Locale locale, Model model) {
        model.addAttribute("list", organizationService.list());
        return "editOrganizations";
    }

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

//    @RequestMapping(value="/", method = RequestMethod.GET)
//    public String viewHome(){
//        return "home";
//    }
//
//    @RequestMapping(value = "/main", method = RequestMethod.GET)
//    public ModelAndView main() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("organizationJSP", new Organization());
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/check-organization")
//    public ModelAndView checkOrganization(@ModelAttribute("organizationJSP") Organization organization) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("secondPage");
//        modelAndView.addObject("organizationJSP", organization);
//        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
//    }

}