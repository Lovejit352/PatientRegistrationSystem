package com.fdmgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.model.User;

@Controller
@SessionAttributes(value = {"loggedUser"}, types = {User.class})
public class AuthenticationController {
	
	@Autowired
	private AuthenticationBusinessLogic authLogic;
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public String processLogin(Model model, @RequestParam String username, @RequestParam String password) {
		if(username==null || password==null || username.equals("") || password.equals("")) {
			model.addAttribute("error", "Fields Cannot Be Empty");
			return "login";
		}
		
		try{
			User user = authLogic.login(username, password);
			
			model.addAttribute("loggedUser", user);
	
			if(user.getUserType().getTypeId()==0) 
				return "redirect:/user";
			else
				return "redirect:/patient";
		}
		catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			return "login";
		}
	}
}
