package com.lanit.satonin18.app.controller.crud;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_in_db.Status;
import com.lanit.satonin18.app.service.entities_service.CrudService;
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
	private CrudService<Organization> organizationService;

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", Arrays.asList(Status.values()));
		return "crud/notificationStatus/list";
	}
}