package com.lanit.satonin18.mvc.controller.crud;

import com.lanit.satonin18.mvc.entity.Organization;
import com.lanit.satonin18.mvc.entity.Action;
import com.lanit.satonin18.mvc.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("actionController")
//@Scope("session")
@RequestMapping("/action")
public class ActionController {

	@Autowired
	private CrudService<Action> actionService;

	//todo need in jsf connected in organizationService
	@Autowired
	private CrudService<Organization> organizationService;

	@GetMapping("/")
	public String index(Model model) {
		return "crud/action/index";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", actionService.list());
		return "crud/action/list";
	}

	//@RequestMapping(value = "/showFormForAdd", method = RequestMethod.GET)
	@GetMapping("/add")
	public String add(Model model){
		model.addAttribute("action", new Action());
		model.addAttribute("listOrg", organizationService.list());
		return "crud/action/add";
	}

	@PostMapping("/add")
	public String addAction(@ModelAttribute("action") Action action){
		actionService.saveOrUpdate(action);
		return "redirect:crud/action/notific_list";
	}
/*
	@ModelAttribute("action")
    public Action formBackingObject() {
        return new Action();
    }

	@PostMapping("/addAction")
	public String saveAction(@ModelAttribute("action") @Valid Action action, BindingResult result, Model entity) {

		if (result.hasErrors()) {
			entity.addAttribute("actions", actionService.notific_list());
			return "crud/editActions";
		}

		actionService.save(action);
		return "redirect:/";
	}
	*/
}