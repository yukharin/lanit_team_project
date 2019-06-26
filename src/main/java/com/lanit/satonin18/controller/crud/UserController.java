package com.lanit.satonin18.controller.crud;

import com.lanit.satonin18.model.Organization;
import com.lanit.satonin18.model.User;
import com.lanit.satonin18.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller("userController")
//@Scope("session")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CrudService<User> userService;

	//todo need in jsf connected in organizationService
	@Autowired
	private CrudService<Organization> organizationService;

	@GetMapping("/")
	public String index(Model model) {
		return "crud/user/index";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", userService.list());
		return "crud/user/list";
	}

	//@RequestMapping(value = "/showFormForAdd", method = RequestMethod.GET)
	@GetMapping("/add")
	public String add(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("listOrg", organizationService.list());
		return "crud/user/add";
	}

	@PostMapping("/add")
	public String addUser(@ModelAttribute("user") User user){
		userService.saveOrUpdate(user);
		return "redirect:crud/user/list";
	}
/*
	@ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }

	@PostMapping("/addUser")
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("users", userService.list());
			return "crud/editUsers";
		}

		userService.save(user);
		return "redirect:/";
	}
	*/
}
