//import com.lanit.satonin18.model.entity.Organization;
//import com.lanit.satonin18.model.service.OrganizationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/organization")
//public class OrganizationController {
//
//    @Autowired
//    private OrganizationService organizationService;
//
//    @GetMapping("/list")
//    public String listOrganizations(Model model) {
//
//        List<Organization> organizations = organizationService.organizations();
//
//        model.addAttribute("listOfOrganizations", organizations);
//
//        return "list-organizations";
//    }
//
//    @RequestMapping(value = "/showFormForAdd", method = RequestMethod.GET)
//    public String showFormForAdd(Model model){
//
//        Organization organization = new Organization();
//
//        model.addAttribute("organization", organization);
//
//        return "organization-form";
//    }
//
//    @PostMapping("/add")
//    public String addOrganization(@ModelAttribute("organization") Organization organization){
//
//        organizationService.addOrganization(organization);
//
//        return "redirect:/organization/list";
//    }
//
//    @GetMapping("showFormForUpdate")
//    public String showFormForUpdate(@RequestParam("organizationId") int id, Model model){
//
//        model.addAttribute("organization", organizationService.getOrganizationById(id));
//
//        return "organization-form";
//    }
//
//    @RequestMapping("delete")
//    public String removeOrganization(@RequestParam("organizationId") int id){
//
//        organizationService.removeOrganization(id);
//
//        return "redirect:/organization/list";
//    }
//
//    @PostMapping("/search")
//    public String searchOrganizations(@RequestParam("theSearchName") String theSearchName,
//                              Model theModel) {
//
//        System.out.println(theSearchName);
//
//        List<Organization> theOrganizations = organizationService.searchOrganizations(theSearchName);
//
//        for(Organization organization : theOrganizations) {
//            System.out.println(organization);
//        }
//
//        theModel.addAttribute("listOfOrganizations", theOrganizations);
//
//        return "list-organizations";
//    }
//
//}