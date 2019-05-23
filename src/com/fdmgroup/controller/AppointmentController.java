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

import com.fdmgroup.businessLogic.AppointmentBusinessLogic;
import com.fdmgroup.businessLogic.DoctorBusinessLogic;
import com.fdmgroup.businessLogic.PatientBusinessLogic;
import com.fdmgroup.businessLogic.PaymentBusinessLogic;
import com.fdmgroup.model.Appointment;
import com.fdmgroup.model.Doctor;
import com.fdmgroup.model.Patient;
import com.fdmgroup.model.User;

@Controller
public class AppointmentController {
	
	@Autowired
	private AppointmentBusinessLogic appointmentLogic;
	
	@Autowired
	private DoctorBusinessLogic doctorLogic;
	
	@Autowired
	private PaymentBusinessLogic paymentLogic;
	
	@Autowired 
	private PatientBusinessLogic patientLogic;
	
	@RequestMapping(value = "/upcomingAppointments", method = RequestMethod.GET)
	public String viewUpcomingAppointments(Model model, @SessionAttribute(value = "loggedUser") User loggedUser) {
		
		List<Appointment> appointments = appointmentLogic.readUpcomingAppointments();

		model.addAttribute("Appointments", appointments);
		return "upcomingAppointments";
	}
	
	@RequestMapping(value = "/allAppointments", method = RequestMethod.GET)
	public String viewAllAppointments(Model model, @SessionAttribute(value = "loggedUser") User loggedUser) {
		
		List<Appointment> appointments = appointmentLogic.readAllAppointments();

		model.addAttribute("Appointments", appointments);
		return "allAppointments";
	}
	
	@RequestMapping(value = "/upcomingAppointments/addAppointment/{patientId}", method = RequestMethod.GET)
	public String addAppointmentForm(Model model, @SessionAttribute("loggedUser") User loggedUser, @PathVariable(value = "patientId") String string_id) {
		int id = Integer.parseInt(string_id);

		Patient patient = patientLogic.readById(id);
		
		model.addAttribute("patFirstName", patient.getFirstName());
		model.addAttribute("patLastName", patient.getLastName());
		
		List<Doctor> doctors = doctorLogic.readAllDoctors();
		System.out.println();
		model.addAttribute("Doctors", doctors);
		
		return "addAppointment";
	}
	
	@RequestMapping(value = "/upcomingAppointments/addAppointment/{patientId}", method = RequestMethod.POST)
	public String addAppointment(Model model, @SessionAttribute("loggedUser") User loggedUser, @RequestParam String doctorId, @RequestParam String appointmentDate, 
			@RequestParam String appointmentTime, @PathVariable(value = "patientId") String string_id){	
		
			int id = Integer.parseInt(string_id);
			Patient patient = patientLogic.readById(id);
		try{
			Doctor doctor = doctorLogic.readById(Integer.parseInt(doctorId));
			int appId = appointmentLogic.bookAppointment(patient, doctor, appointmentDate, appointmentTime);
			Appointment appointment = appointmentLogic.getAppointmentById(appId);
			double amount = appointment.getDoctor().getConsultationFee();
			
			paymentLogic.makePayment(appointment, amount);
		}
		catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("patFirstName", patient.getFirstName());
			model.addAttribute("patLastName", patient.getLastName());
			List<Doctor> doctors = doctorLogic.readAllDoctors();

			model.addAttribute("Doctors", doctors);
			return "addAppointment";
		}
		
		return "redirect:/allAppointments";
	}
	
	@RequestMapping(value = "/allAppointments/updateAppointment/{appointmentId}", method = RequestMethod.GET)
	public String updateAppointmentForm(Model model, @SessionAttribute("loggedUser") User loggedUser, 
			@PathVariable(value = "appointmentId") String string_id) {
		
		int id = Integer.parseInt(string_id);

		Appointment appointment = appointmentLogic.readById(id);
		
		List<Doctor> doctors = doctorLogic.readAllDoctors();
		
		model.addAttribute("id", id);
		model.addAttribute("patientFirstName", appointment.getPatient().getFirstName());
		model.addAttribute("patientLastName", appointment.getPatient().getLastName());
		model.addAttribute("Doctors", doctors);
		model.addAttribute("appointment", appointment);
		model.addAttribute("appDate", appointment.getAppointmentDate());
		model.addAttribute("appTime", appointment.getAppointmentTime());
		
		return "updateAppointment";
	}
	
	@RequestMapping(value = "/allAppointments/updateAppointment/{appointmentId}", method = RequestMethod.POST)
	public String updateAppointment(Model model, @SessionAttribute("loggedUser") User loggedUser, 
			@PathVariable(value = "appointmentId") String string_id, @RequestParam String doctorId, @RequestParam String date,
			@RequestParam String time) {
		
		int id = Integer.parseInt(string_id);
		
		Appointment appointment = appointmentLogic.readById(id);
		Doctor doctor_old = appointment.getDoctor();
		Doctor doctor = doctorLogic.readById(Integer.parseInt(doctorId));
		
		List<Doctor> doctors = doctorLogic.readAllDoctors();
		
		try {
			appointmentLogic.updateAppointment(appointment, doctor, date, time);
			paymentLogic.updatedAppointmentPayment(appointment, doctor_old, doctor);
		}
		catch(IllegalArgumentException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("patientFirstName", appointment.getPatient().getFirstName());
			model.addAttribute("patientLastName", appointment.getPatient().getLastName());
			model.addAttribute("Doctors", doctors);
			model.addAttribute("appointment", appointment);
			model.addAttribute("appDate", appointment.getAppointmentDate());
			model.addAttribute("appTime", appointment.getAppointmentTime());
			return "updateAppointment";
		}
		
		return "redirect:/allAppointments";
	}
}
