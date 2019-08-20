package com.lanit.satonin18.app.controller.crud;

import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller("notificationStatusController")
@RequestMapping("/crud/notificationStatus")
public class NotificationStatusController {

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", Arrays.asList(Status.values()));
		return "crud/notificationStatus/list";
	}

//	@PostMapping("/search")
//	public String searchUsers(@RequestParam("theSearchName") String theSearchName,
//							  Model theModel) {
//		System.out.println(theSearchName);
//		List<User> theUsers = userService.findByLastNameIgnoreCaseContaining(theSearchName);
//		for(User user : theUsers) {
//			System.out.println(user);
//		}
//		theModel.addAttribute("list", theUsers);
//		theModel.addAttribute("valueSearch", theSearchName);
//		return "crud/notificationStatus/list";
//	}
}
