package com.lanit.satonin18.app.controller.crud;

import com.lanit.satonin18.app.entity.Organization;
import com.lanit.satonin18.app.entity.no_db.Status;
import com.lanit.satonin18.app.service.entities_service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

//TODO исправить веблюжий стиль запросов на другой

@Controller("notificationStatusController")
//@Scope("session")
@RequestMapping("/notificationStatus")
public class NotificationStatusController {

//	@Autowired
//	private CrudService<Status> notificationStatusService;

	//todo need in jsf connected in organizationService
	@Autowired
	private CrudService<Organization> organizationService;

	@GetMapping("/")
	public String index(Model model) {
		return "crud/notificationStatus/index";
	}

	@GetMapping("/list")
	public String list(Model model) {
//		model.addAttribute("list", notificationStatusService.list());
		model.addAttribute("list", Arrays.asList(Status.values()));
		return "crud/notificationStatus/list";
	}

	//@RequestMapping(value = "/showFormForAdd", method = RequestMethod.GET)
//	@GetMapping("/add")
//	public String add(Model model){
//		model.addAttribute("notificationStatus", new Status());
//		model.addAttribute("listOrg", organizationService.list());
//		return "crud/notificationStatus/add";
//	}
//
//	@PostMapping("/add")
//	public String addNotificationStatus(@ModelAttribute("notificationStatus") Status status){
//		notificationStatusService.saveOrUpdate(status);
//		return "redirect:crud/status/notific_list";
//	}
/*
	@ModelAttribute("statusAfterProcessing")
    public Status formBackingObject() {
        return new Status();
    }

	@PostMapping("/addNotificationStatus")
	public String saveNotificationStatus(@ModelAttribute("statusAfterProcessing") @Valid Status statusAfterProcessing, BindingResult result, Model entity) {

		if (result.hasErrors()) {
			entity.addAttribute("notificationStatuss", notificationStatusService.notific_list());
			return "crud/editNotificationStatuss";
		}

		notificationStatusService.save(statusAfterProcessing);
		return "redirect:/";
	}
	*/
}
