package com.fdmgroup.businessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fdmgroup.dao.jpa.AppointmentJpaDao;
import com.fdmgroup.dao.jpa.DoctorJpaDao;
import com.fdmgroup.dao.jpa.PatientJpaDao;
import com.fdmgroup.dao.jpa.PaymentJpaDao;
import com.fdmgroup.model.Appointment;
import com.fdmgroup.model.Doctor;
import com.fdmgroup.model.Patient;

public class AppointmentBusinessLogic {
	private AppointmentJpaDao appointementDao;
	PatientJpaDao patientDao;
	DoctorJpaDao doctorDao;
	PaymentJpaDao paymentDao;
	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatTime = new SimpleDateFormat("kk:mm");

	public AppointmentBusinessLogic() {
		appointementDao = new AppointmentJpaDao();
		patientDao = new PatientJpaDao();
		doctorDao = new DoctorJpaDao();
		paymentDao = new PaymentJpaDao();
	}

	public int bookAppointment(Patient patient, Doctor doctor, String date, String time) throws IllegalArgumentException{		
		String errorText = null;
		Date appointmentDate = null;
		Date appointmentTime = null;
		
		try {
			appointmentDate = formatDate.parse(date);			
		} catch (ParseException e) {
			errorText = "Invalid format for date";
		}
		
		try {
			appointmentTime = formatTime.parse(time);			
		} catch (ParseException e) {
			errorText = "Invalid format for time";
		}
		
		if(appointmentDate.compareTo(new Date())<0) {
			errorText =  "Appointment cannot be booked before today";
		}
		if(appointmentDate.compareTo(new Date())==0) {
			errorText = "Appointment cannot be booked today";
		}
		
		List<Appointment> allAppointments = readAllAppointments();
		if(allAppointments!=null) {
			List<Appointment> appointments = getAppointmentsByDoctorAndDateAndTime(appointmentDate, appointmentTime, doctor.getDoctor_id());
			if(appointments!=null) {
				errorText = doctor.getFirstName() + " is not available. Select another time please";
			}
		}
		
		if(errorText!=null) {
			throw new IllegalArgumentException(errorText);
		}
		
		Appointment appointment = new Appointment().setAppointmentDate(appointmentDate)
				.setAppointmentTime(appointmentTime).setDoctor(doctor).setPatient(patient);
		appointementDao.create(appointment);
		return appointment.getAppointmentId();
	}
	
	public Appointment getAppointmentById(int appointment_id) {
		return appointementDao.readById(appointment_id);
	}

	public int updateAppointment(Appointment appointment, Doctor doctor, String date, String time) throws IllegalArgumentException{
		Date appointmentDate = null;
		Date appointmentTime = null;
		
		String errorText = null;
		try {
			appointmentDate = formatDate.parse(date);			
		} catch (ParseException e) {
			errorText = "Invalid Format For Date";
		}
		
		try {
			appointmentTime = formatTime.parse(time);			
		} catch (ParseException e) {
			errorText = "Invalid Format For Time";
		}
		
		if(appointmentDate.compareTo(new Date())<0) {
			errorText = "Appointment cannot be booked before today";
		}
		if(appointmentDate.compareTo(new Date())==0) {
			errorText = "Appointment cannot be booked today";
		}
		
		List<Appointment> appointments = getAppointmentsByDoctorAndDateAndTime(appointmentDate, appointmentTime, doctor.getDoctor_id());
		if(appointments!=null)
			errorText = doctor.getFirstName() + " is not available. Select another time please....";
			
		if(errorText!=null)
			throw new IllegalArgumentException(errorText);
		
		appointment.setAppointmentDate(appointmentDate).setAppointmentTime(appointmentTime).setDoctor(doctor);
		appointementDao.update(appointment);
		return appointment.getAppointmentId();
	}

	public List<Appointment> readAllAppointments() {
		List<Appointment> appointments = appointementDao.readAll();
		if(appointments==null) {
			return null;
		}
		else {
			return appointments;
		}
	}

	public List<Appointment> getAppointmentsByDoctorAndDateAndTime(Date appDate, Date appTime, int doc_id) {
		return appointementDao.getAppointmentsByDoctorAndDateAndTime(appDate, appTime, doc_id);
	}

	public List<Appointment> getAppointmentsByPatientAndDateAndTime(int pat_id, Date appSqlDate, Date appSqlTime) {
		return appointementDao.getAppointmentsByPatientAndDateAndTime(pat_id, appSqlDate, appSqlTime);
	}

	public List<Appointment> readUpcomingAppointments() {
		List<Appointment> appointments = appointementDao.readUpcomingAppointments();
		if(appointments==null) {
			return null;
		}
		else {
			return appointments;
		}
	}

	public Appointment readById(int id) {
		return appointementDao.readById(id);
	}
}
