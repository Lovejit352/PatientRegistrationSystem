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
import com.fdmgroup.businessLogic.PatientBusinessLogic;
import com.fdmgroup.model.Patient;
import com.fdmgroup.model.User;

@Controller
public class PatientController {
	
	@Autowired
	private PatientBusinessLogic patientLogic;
	
	@Autowired
	private AuthenticationBusinessLogic authLogic;
	
	@RequestMapping(value = "/patient", method = RequestMethod.GET)
	public String viewAllPatients(Model model, @SessionAttribute("loggedUser") User loggedUser) {
		List<Patient> patients = patientLogic.readAllPatients();

		model.addAttribute("Patients", patients);
		return "patient";
	}
	
	@RequestMapping(value = "/patient/addPatient", method = RequestMethod.GET)
	public String addPatientForm(@SessionAttribute("loggedUser") User loggedUser) {
		return "addPatient";
	}
	
	@RequestMapping(value = "/patient/addPatient", method = RequestMethod.POST)
	public String addPatient(Model model, @SessionAttribute("loggedUser") User loggedUser, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String contact, @RequestParam String email, @RequestParam String address, @RequestParam String birthdate) {	
		try{
			authLogic.patientValidations(firstname, lastname, email, contact, address);
			patientLogic.register(firstname, lastname, email, birthdate, address, contact, loggedUser);
		}catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			return "addPatient";
		}
		return "redirect:/patient";
	}
	
	@RequestMapping(value = "/patient/updatePatient/{patientId}", method = RequestMethod.GET)
	public String updatePatientForm(Model model, @SessionAttribute("loggedUser") User loggedUser, 
			@PathVariable(value = "patientId") String string_id) {
		int id = Integer.parseInt(string_id);
		
		Patient patient = patientLogic.readById(id);
		
		model.addAttribute("firstName", patient.getFirstName());
		model.addAttribute("lastName", patient.getLastName());
		model.addAttribute("contact", patient.getContactNo());
		model.addAttribute("email", patient.getEmail());
		model.addAttribute("address", patient.getAddress());
		model.addAttribute("birthdate", patient.getBirthDate());
		
		return "updatePatient";
	}
	
	@RequestMapping(value = "/patient/updatePatient/{patientId}", method = RequestMethod.POST)
	public String updatePatient(Model model, @SessionAttribute("loggedUser") User loggedUser, 
			@PathVariable(value = "patientId") String string_id, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String contact, @RequestParam String email, @RequestParam String address, @RequestParam String birthdate) {
		int id = Integer.parseInt(string_id);
		
		Patient patient = patientLogic.readById(id);
		
		try{
			authLogic.patientValidations(firstname, lastname, email, contact, address);
			patientLogic.update(patient, firstname, lastname, email, birthdate, address, contact);
		}catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("firstName", patient.getFirstName());
			model.addAttribute("lastName", patient.getLastName());
			model.addAttribute("contact", patient.getContactNo());
			model.addAttribute("email", patient.getEmail());
			model.addAttribute("address", patient.getAddress());
			model.addAttribute("birthdate", patient.getBirthDate());
			return "updatePatient";
		}
	
		return "redirect:/patient";
	}
}
