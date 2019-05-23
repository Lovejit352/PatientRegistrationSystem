package com.fdmgroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.model.User;

@Controller
@SessionAttributes(value = {"loggedUser"}, types = {User.class})
public class Homecontroller {

	@RequestMapping(value = "/")
	public String showHome(Model model) {
		model.addAttribute("loggedUser", null);
		return "login";
	}
}
