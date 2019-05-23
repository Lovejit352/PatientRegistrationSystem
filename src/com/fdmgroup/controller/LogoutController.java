package com.fdmgroup.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class LogoutController {
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String processLogout(HttpSession session, SessionStatus sessionStatus) {
		session.invalidate();
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
