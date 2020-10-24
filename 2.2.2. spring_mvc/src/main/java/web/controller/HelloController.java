package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

//	@GetMapping(value = "/")
//	public String printWelcome(ModelMap model) {
//		List<String> messages = new ArrayList<>();
//		messages.add("Hello!");
//		messages.add("I'm Spring MVC application");
//		messages.add("5.2.0 version by sep'19 ");
//		model.addAttribute("messages", messages);
//		return "/WEB-INF/oldPages/index.html";
//	}

	@RequestMapping(value = {"/","/users"}, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		model.addAttribute("listUsers", this.userService.listUsers());
		model.addAttribute("user", new User());
		return "users";
	}

	@GetMapping(value = "/add")
	public String addUser(@ModelAttribute("user") User user) {
//		System.out.println(user.toString());
		this.userService.add(user);
		return "redirect:/";
	}

	@GetMapping("/remove")
	public String removeUser(@RequestParam("id") Long id) {
		this.userService.removeById(id);
		return "redirect:/";
	}

	@GetMapping(value = "/editUser")
public String editUser(ModelMap model, @RequestParam("id") Long id) {
	User user = userService.getUserById(id);
	model.addAttribute("user", user);
	return "/editUser";
}

	@GetMapping(value = "/edit")
	public String edit(@ModelAttribute("user") User user) {
		userService.edit(user);
		return "redirect:/";
	}
}
