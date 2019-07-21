package com.lanit.satonin18.mvc.controller.crud;

import com.lanit.satonin18.mvc.entity.Organization;
import com.lanit.satonin18.mvc.entity.User;
import com.lanit.satonin18.mvc.service.CrudService;
import com.lanit.satonin18.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Scope("session")
//@SessionAttributes(value = "user")

@Controller("userController")
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
	@GetMapping("/delete")
	public String removeUser(@RequestParam("userId") int id){
		userService.delete(id);
		return "redirect:list";
	}
	@GetMapping("/add")
	public String add(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("listOrg", organizationService.list());
		return "crud/user/form_add";
	}
	@GetMapping("/apply_add")
	public String apply_add(
//			@RequestParam("id")  int id,
			@RequestParam("firstName")  String  firstName,
			@RequestParam("lastName")  String  lastName,
			@RequestParam("idOrg")  int idOrg
//			@ModelAttribute("user") User user
	){
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setOrganization(organizationService.getById(idOrg));

		userService.saveOrUpdate(user);
		System.out.println(user);
		return "redirect:list";
	}
	@GetMapping("/update")
	public String update(@RequestParam("userId") int id, Model model){
		model.addAttribute("user", userService.getById(id));
		model.addAttribute("listOrg", organizationService.list());
		return "crud/user/form_update";
	}
	@GetMapping("/apply_update")
	public String apply_update(
			@RequestParam("id")  int id,
			@RequestParam("firstName")  String  firstName,
			@RequestParam("lastName")  String  lastName,
			@RequestParam("idOrg")  int idOrg
//			@ModelAttribute("user") User user
	){
		User user = userService.getById(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setOrganization(organizationService.getById(idOrg));

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
		theModel.addAttribute("valueSearch", theSearchName);
		return "crud/user/list";
	}
}
