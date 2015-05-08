package ua.vkravchenko.bc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required=false) String authFailed) {
		String message="";
		if(authFailed != null) {
			message = "Invalid username or password, try again !";
			System.out.println("Auth failed");
		}
		model.addAttribute("message", message);
		return "login";
	}
	
	
}
