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
		return "redirect:listByFilterOrg_Order_Pagination";
	}
	@GetMapping("/add")
	public String add(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("listOrg", organizationService.list());
		return "crud/user/form_add";
	}
	@PostMapping("/add")
	public String save(
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
		return "redirect:listByFilterOrg_Order_Pagination";
	}
	@GetMapping("/update")
	public String update(@RequestParam("userId") int id, Model model){
		model.addAttribute("user", userService.getById(id));
		model.addAttribute("listOrg", organizationService.list());
		return "crud/user/form_update";
	}
	@PostMapping("/update")
	public String edit(
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
		return "redirect:listByFilterOrg_Order_Pagination";
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
