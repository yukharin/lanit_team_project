package com.lanit.satonin18.controller.crud;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lanit.satonin18.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@Scope("session")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {
		return "crud/user/index";
	}
	@GetMapping("/list")
	public String list(Locale locale, Model model) {
		model.addAttribute("list", userService.list());
		return "crud/user/list";
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
