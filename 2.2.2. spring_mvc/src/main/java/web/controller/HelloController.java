package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.config.AppConfig;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService){ this.userService = userService; }

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "index";
	}

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String listUsers(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("listUsers", this.userService.listUsers());
		return "users";
	}

	@RequestMapping(value = "users/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute ("user") User user){
			this.userService.add(user);
		return "redirect:/users";
	}

	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable("id") Long id){
		this.userService.removeById(id);
		return "redirect:/users";
	}

	@RequestMapping("edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model){
		model.addAttribute("user", this.userService.getUserById(id));
		model.addAttribute("listUsers", this.userService.listUsers());
		return "users";
	}


	
}