package com.lanit.satonin18.app.controller.crud;

import com.lanit.satonin18.app.entity.Action;
import com.lanit.satonin18.app.service.entities_service.ActionService;
import com.lanit.satonin18.app.service.entities_service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("actionController")
@RequestMapping("/crud/action")
public class ActionController {

	@Autowired
	private ActionService actionService;

	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", actionService.findAll());
		return "crud/action/list";
	}

//	@PostMapping("/search")
//	public String searchUsers(@RequestParam("theSearchName") String theSearchName,
//							  Model theModel) {
//		System.out.println(theSearchName);
//		List<Action> theActions = actionService.findByLastContentIgnoreCaseContaining(theSearchName);
//		for(Action action : theActions) {
//			System.out.println(action);
//		}
//		theModel.addAttribute("list", theActions);
//		theModel.addAttribute("valueSearch", theSearchName);
//		return "crud/action/list";
//	}
}
