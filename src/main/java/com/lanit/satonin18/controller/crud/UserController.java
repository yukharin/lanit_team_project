package com.lanit.satonin18.controller.crud;

import com.lanit.satonin18.model.Organization;
import com.lanit.satonin18.model.User;
import com.lanit.satonin18.service.CrudService;
import com.lanit.satonin18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller("userController")
//@Scope("session")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

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
	@RequestMapping("delete")
	public String removeUser(@RequestParam("userId") int id){
		userService.delete(id);
		return "redirect:list";
	}
	@GetMapping("/add")
	public String add(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("listOrg", organizationService.list());
		return "crud/user/form";
	}
	@GetMapping("update")
	public String update(@RequestParam("userId") int id, Model model){
		model.addAttribute("user", userService.getById(id));
		model.addAttribute("listOrg", organizationService.list());
		return "crud/user/form";
	}
	//TODO NOT WORKING POST SUBMIT INNER OBJECT(FROM SELECTED)
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(
//			@RequestParam("id")  int id,
//			@RequestParam("firstName")  String  firstName,
//			@RequestParam("lastName")  String  lastName,
//			@RequestParam("userOrg")  int id_userOrg,
//			Model model,

			@ModelAttribute("user") User user
	){
// TODO VERY BAD!!!
//		user.setUserOrg(organizationService.getById(id_userOrg));

		userService.saveOrUpdate(user);
		System.out.println(user);
		return "redirect:list";
	}
	@PostMapping("/search")
	public String searchUsers(@RequestParam("theSearchName") String theSearchName,
							  Model theModel) {
		System.out.println(theSearchName);
		List<User> theUsers = userService.searchUserByLastName(theSearchName);
		for(User user : theUsers) {
			System.out.println(user);
		}
		theModel.addAttribute("list", theUsers);
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
