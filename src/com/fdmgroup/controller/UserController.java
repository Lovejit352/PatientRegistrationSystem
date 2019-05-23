package com.fdmgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fdmgroup.businessLogic.AuthenticationBusinessLogic;
import com.fdmgroup.businessLogic.UserBusinessLogic;
import com.fdmgroup.model.User;

@Controller
public class UserController {
	
	@Autowired
	private UserBusinessLogic userLogic;
	
	@Autowired
	private AuthenticationBusinessLogic authLogic;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String viewAllUsers(Model model, @SessionAttribute("loggedUser") User loggedUser) {	
		if(loggedUser.getUserType().getDescription().equals("Receptionist"))
			return "redirect:/patient";
		
		List<User> users = userLogic.displayAllUsers();
		
		model.addAttribute("Users", users);
		return "user";
	}
	
	@RequestMapping(value = "/user/addUser", method = RequestMethod.GET)
	public String addUserForm(@SessionAttribute("loggedUser") User loggedUser) {
		if(loggedUser.getUserType().getDescription().equals("Receptionist"))
			return "redirect:/patient";
		
		return "addUser";
	}
	
	@RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
	public String addUser(Model model, @SessionAttribute("loggedUser") User loggedUser, @RequestParam String username, @RequestParam String password,
			@RequestParam String firstname, @RequestParam String lastname, @RequestParam String contact, @RequestParam String email,
			@RequestParam String address, @RequestParam String usertype) {			
		try{
			authLogic.userValidations(firstname, lastname, email, contact, address, usertype);		
			userLogic.register(username, password, firstname, lastname, email, contact, address, usertype, loggedUser);
		}catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			return "addUser";
		}
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/user/updateUser/{userId}", method = RequestMethod.GET)
	public String updateUserForm(Model model, @SessionAttribute("loggedUser") User loggedUser, 
			@PathVariable(value = "userId") String string_id) {
		if(loggedUser.getUserType().getDescription().equals("Receptionist"))
			return "redirect:/patient";
		
		int id = Integer.parseInt(string_id);
		
		User user = userLogic.readById(id);
		
		model.addAttribute("id", id);
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("contact", user.getContactNo());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("address", user.getAddress());
		model.addAttribute("userType", user.getUserType());
		
		return "updateUser";
	}
	
	@RequestMapping(value = "/user/updateUser/{userId}", method = RequestMethod.POST)
	public String updateUser(Model model, @SessionAttribute("loggedUser") User loggedUser, 
			@PathVariable(value = "userId") String string_id, @RequestParam String address, @RequestParam String firstname,
			@RequestParam String lastname, @RequestParam String contact, @RequestParam String email, @RequestParam String usertype) {		
		int id = Integer.parseInt(string_id);
		
		User user = userLogic.readById(id);
		
		try{
			authLogic.userValidations(firstname, lastname, email, contact, address, usertype);
		}catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("id", id);
			model.addAttribute("firstName", user.getFirstName());
			model.addAttribute("lastName", user.getLastName());
			model.addAttribute("contact", user.getContactNo());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("address", user.getAddress());
			model.addAttribute("userType", user.getUserType());
			return "updateUser";
		}
		userLogic.update(user, id, firstname, lastname, email, contact, address, usertype);
	
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/user/updateUser/resetPassword/{userId}", method = RequestMethod.GET)
	public String resetUserPasswordForm	(Model model, @SessionAttribute("loggedUser") User loggedUser, @PathVariable(value = "userId") String string_id) {
		if(loggedUser.getUserType().getDescription().equals("Receptionist"))
			return "redirect:/patient";
		
		int id = Integer.parseInt(string_id);
		
		model.addAttribute("id", id);
		return "resetPassword";
	}
	
	@RequestMapping(value = "/user/updateUser/resetPassword/{userId}", method = RequestMethod.POST)
	public String resetUserPassword(Model model, @PathVariable(value = "userId") String string_id, @RequestParam String newpassword,
			@RequestParam String confirmpassword) {

		int id = Integer.parseInt(string_id);
		
		try{
			authLogic.changeUserPassword(id, newpassword, confirmpassword);
		}catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("id", id);
			return "resetPassword";
		}
		
		model.addAttribute("message", "Password Changed");
		return "redirect:/user/updateUser/"+id;
	}
}
