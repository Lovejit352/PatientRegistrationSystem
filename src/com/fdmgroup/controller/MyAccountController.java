package com.fdmgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.businessLogic.UserBusinessLogic;
import com.fdmgroup.model.User;

@Controller
public class MyAccountController {
	
	@Autowired
	private UserBusinessLogic userLogic;
	
	@Autowired
	private AuthenticationBusinessLogic authLogic;
	
	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public String viewAccountDetails(Model model, @SessionAttribute("loggedUser") User loggedUser) {	
		
		model.addAttribute("loggedUser", loggedUser);
		model.addAttribute("username", loggedUser.getUserName());
		model.addAttribute("firstname", loggedUser.getFirstName());
		model.addAttribute("lastname", loggedUser.getLastName());
		model.addAttribute("contact", loggedUser.getContactNo());
		model.addAttribute("email", loggedUser.getEmail());
		model.addAttribute("address", loggedUser.getAddress());
		model.addAttribute("usertype", loggedUser.getUserType().getDescription());

		return "myAccount";
	}
	
	@RequestMapping(value = "/myAccount/editAccount", method = RequestMethod.GET)
	public String editCurrentUserDetailsForm(Model model, @SessionAttribute("loggedUser") User loggedUser) {	
		
		model.addAttribute("efirstname", loggedUser.getFirstName());
		model.addAttribute("elastname", loggedUser.getLastName());
		model.addAttribute("econtact", loggedUser.getContactNo());
		model.addAttribute("eemail", loggedUser.getEmail());
		model.addAttribute("eaddress", loggedUser.getAddress());
		
		return "editAccount";
	}
	
	@RequestMapping(value = "/myAccount/editAccount", method = RequestMethod.POST)
	public String editCurrentUserDetails(Model model, @SessionAttribute("loggedUser") User loggedUser, @RequestParam String firstname, 
			@RequestParam String lastname, @RequestParam String contact, @RequestParam String email, @RequestParam String address) {	
		
		try{
			authLogic.userValidations(firstname, lastname, email, contact, address, String.valueOf(loggedUser.getUserType()));
			userLogic.update(loggedUser, loggedUser.getUserId(), firstname, lastname, email, contact, address, String.valueOf(loggedUser.getUserType()));
		}catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("efirstname", loggedUser.getFirstName());
			model.addAttribute("elastname", loggedUser.getLastName());
			model.addAttribute("econtact", loggedUser.getContactNo());
			model.addAttribute("eemail", loggedUser.getEmail());
			model.addAttribute("eaddress", loggedUser.getAddress());
			return "editAccount";
		}
		
		return "redirect:/myAccount";
	}
	
	@RequestMapping(value = "/myAccount/changePassword", method = RequestMethod.GET)
	public String changePasswordForm(Model model, @SessionAttribute("loggedUser") User loggedUser) {	
		
		return "changePassword";
	}
	
	@RequestMapping(value = "/myAccount/changePassword", method = RequestMethod.POST)
	public String changePassword(Model model, @SessionAttribute("loggedUser") User loggedUser, @RequestParam String oldPassword, @RequestParam String newPassword,
			@RequestParam String confirmPassword) {	
		
		try{
			authLogic.changePassword(loggedUser, oldPassword, newPassword, confirmPassword);
		}catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("loggedUser", loggedUser);
			return "changePassword";
		}
		
		return "redirect:/myAccount";
	}
}
