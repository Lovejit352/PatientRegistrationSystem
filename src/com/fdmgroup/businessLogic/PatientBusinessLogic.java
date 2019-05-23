package com.fdmgroup.businessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fdmgroup.dao.jpa.PatientJpaDao;
import com.fdmgroup.model.Patient;
import com.fdmgroup.model.User;

public class PatientBusinessLogic {
	private PatientJpaDao patientDao;

	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public PatientBusinessLogic() {
		patientDao = new PatientJpaDao();
	}

	public void register(String firstName, String lastName, String email, String date, String address,
			String contact, User user) throws IllegalArgumentException{
		String error = null;
		Date birthDate = null;
		try {
			birthDate = formatDate.parse(date);			
		} catch (ParseException e) {
			error = "Invalid format for date";
		}
		
		if(birthDate.compareTo(new Date())>0) {
			error =  "Birthdate cannot be ahead of today";
		}
		if(birthDate.compareTo(new Date())==0) {
			error = "Birthdate cannot be today";
		}
		
		List<Patient> listPatient = patientDao.readAll();
		if(listPatient!=null) {
			for(Patient patient : listPatient) {
				String fullName = patient.getFirstName().toLowerCase()+patient.getLastName().toLowerCase();
				if(fullName.equals(firstName.toLowerCase()+lastName.toLowerCase()))
					error = "Patient With Same Name Already Exists";
			}
		}
		
		if(error!=null)
			throw new IllegalArgumentException(error);
		
		Patient patient = new Patient().setFirstName(firstName).setLastName(lastName).setEmail(email)
				.setBirthDate(birthDate).setAddress(address).setContactNo(contact).setRegistrationDate(new java.util.Date())
				.setCreatedBy(user);
		patientDao.create(patient);
	}

	public void update(Patient patient, String firstName, String lastName, String email, String date,
			String address, String contact) throws IllegalArgumentException{
		Date birthDate = null;
		String error = null;
		try {
			birthDate = formatDate.parse(date);			
		} catch (ParseException e) {
			error = "Invalid format for date";
		}
		
		List<Patient> listPatient = patientDao.readAll();
		for(Patient patientList : listPatient) {
			String fullName = patientList.getFirstName().toLowerCase()+patientList.getLastName().toLowerCase();
			if(fullName.equals(firstName.toLowerCase()+lastName.toLowerCase()) && patientList.getPatientId()!=patient.getPatientId())
				error = "Patient With Same Name Already Exists";
		}
		
		if(error!=null)
			throw new IllegalArgumentException(error);
		
		patient.setFirstName(firstName).setLastName(lastName).setEmail(email)
				.setBirthDate(birthDate).setContactNo(contact).setAddress(address);
		patientDao.update(patient);
	}

	public Patient readById(int patient_id) {
		if(patient_id==0)
			return null;
		return patientDao.readById(patient_id);
	}
	
	public List<Patient> readAllPatients() {
		List<Patient> patients = patientDao.readAll();
		if(patients.size()==0) {
			return null;
		}
		else {
			return patients;
		}
	}

	public void readByEmail(String email) {
		Patient patient = patientDao.readByEmail(email);
		System.out.println(patient.toString());
	}

	public Patient readByFirstNameAndLastName(String patientFirstName, String patientLastName) throws IllegalArgumentException{
		Patient patient = patientDao.readByFirstNameAndLastName(patientFirstName, patientLastName);
		if(patient==null)
			throw new IllegalArgumentException("Patient does not exist");
		return patient;		
	}
}
