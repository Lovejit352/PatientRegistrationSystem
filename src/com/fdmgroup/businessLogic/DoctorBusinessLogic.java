package com.fdmgroup.businessLogic;

import java.util.List;

import com.fdmgroup.dao.jpa.DoctorJpaDao;
import com.fdmgroup.model.Doctor;

public class DoctorBusinessLogic {
private DoctorJpaDao doctorDao;
	
	public DoctorBusinessLogic() {
		doctorDao = new DoctorJpaDao();
	}
	public Doctor readById(int doctor_id) {
		Doctor doctor =  doctorDao.readById(doctor_id);
		return doctor;
	}
	
	public List<Doctor> readAllDoctors() {
		List<Doctor> doctors = doctorDao.readAll();
		if(doctors==null)
			return null;
		return doctors;
	}
	public Doctor readByFirstNameAndLastName(String doctorFirstName, String doctorLastName) {
		return doctorDao.readByFirstNameAndLastName(doctorFirstName, doctorLastName);
	}
}
