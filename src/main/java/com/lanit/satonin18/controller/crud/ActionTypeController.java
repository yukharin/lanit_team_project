package com.lanit.satonin18.controller.crud;

import com.lanit.satonin18.model.Organization;
import com.lanit.satonin18.model.ActionType;
import com.lanit.satonin18.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("actionTypeController")
//@Scope("session")
@RequestMapping("/actionType")
public class ActionTypeController {

	@Autowired
	private CrudService<ActionType> actionTypeService;

	//todo need in jsf connected in organizationService
	@Autowired
	private CrudService<Organization> organizationService;

	@GetMapping("/")
	public String index(Model model) {
		return "crud/actionType/index";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", actionTypeService.list());
		return "crud/actionType/list";
	}

	//@RequestMapping(value = "/showFormForAdd", method = RequestMethod.GET)
	@GetMapping("/add")
	public String add(Model model){
		model.addAttribute("actionType", new ActionType());
		model.addAttribute("listOrg", organizationService.list());
		return "crud/actionType/add";
	}

	@PostMapping("/add")
	public String addActionType(@ModelAttribute("actionType") ActionType actionType){
		actionTypeService.saveOrUpdate(actionType);
		return "redirect:crud/actionType/list";
	}
/*
	@ModelAttribute("actionType")
    public ActionType formBackingObject() {
        return new ActionType();
    }

	@PostMapping("/addActionType")
	public String saveActionType(@ModelAttribute("actionType") @Valid ActionType actionType, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("actionTypes", actionTypeService.list());
			return "crud/editActionTypes";
		}

		actionTypeService.save(actionType);
		return "redirect:/";
	}
	*/
}
