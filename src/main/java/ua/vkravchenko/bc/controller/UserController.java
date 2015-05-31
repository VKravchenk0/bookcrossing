package ua.vkravchenko.bc.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}

	@RequestMapping("/account")
	public String account(Model model, Principal principal) {
		
		String email = principal.getName();
		User user = userService.findOne(email);
		model.addAttribute("user", user);
		model.addAttribute("owner", true);
		return "account";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String changeInfo(Model model, @ModelAttribute("user") User user, Principal principal) {
		
		String email = principal.getName();
		userService.save(email, user);
		return "redirect:/account";
	}
	
	
}
