package ua.vkravchenko.bc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.vkravchenko.bc.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", params = {"page"})
	public String users(Model model, @RequestParam(value="page", required=false) Integer page) {
		if (page < 0) {
			page = 0;
		}
		model.addAttribute("users", userService.findOnPage(page));
		model.addAttribute("page", page);
		model.addAttribute("paginationList", getPaginationList(page));
		return "users";
	}
	
	@RequestMapping(value = "/users")
	public String users(Model model) {
		int page = 0;
		model.addAttribute("users", userService.findOnPage(0));
		model.addAttribute("page", page);
		model.addAttribute("paginationList", getPaginationList(page));
		return "users";
	}
	
	private List<Integer> getPaginationList(int page) {
		List<Integer> list = new ArrayList<Integer>(5);
		int i = -2;
		int currentIndex = 0;
		
		while (i < 3) {
			if (page + i >= 0) {
				list.add(currentIndex, page + i);
				currentIndex++;
			}
			i++;
		}
		while (currentIndex < 5) {
			list.add(currentIndex, page + i);
			currentIndex++;
			i++;
		}
		return list;
	}
	
}
