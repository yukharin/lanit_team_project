package com.lanit.satonin18.app.controller.crud;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_in_db.ActionType;
import com.lanit.satonin18.app.service.entities_service.CrudService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller("actionTypeController")
@RequestMapping("/crud/actionType")
public class ActionTypeController {

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/")
	public String index(Model model) {
		return "crud/actionType/afterLogin";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", Arrays.asList(ActionType.values()));
		return "crud/actionType/list";
	}

//	@PostMapping("/search")
//	public String searchUsers(@RequestParam("theSearchName") String theSearchName,
//							  Model theModel) {
//		System.out.println(theSearchName);
//		List<ActionType> theUsers = userService.findByLastNameIgnoreCaseContaining(theSearchName);
//		for(User user : theUsers) {
//			System.out.println(user);
//		}
//		theModel.addAttribute("list", theUsers);
//		theModel.addAttribute("valueSearch", theSearchName);
//		return "crud//list";
//	}
}
