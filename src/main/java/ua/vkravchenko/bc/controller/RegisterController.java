package ua.vkravchenko.bc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	// mapping to form:form commandName="user" in register form
	@ModelAttribute("user")
	public User construct() {
		return new User();
	}

	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}
		userService.save(user);
		return "redirect:/register?success=true";
	}
	
	@RequestMapping("/register/available")
	@ResponseBody
	public String available(@RequestParam String email) {
		Boolean available = userService.findOne(email) == null;
		return available.toString();
	}
	
	
}
