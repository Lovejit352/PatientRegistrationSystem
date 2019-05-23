package com.fdmgroup.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.IDoctorDao;
import com.fdmgroup.model.Doctor;

public class DoctorJpaDao implements IDoctorDao{

	@Override
	public Doctor readById(int id) {
		EntityManager em = ConnectionManager.getEntityManager();
		Doctor doctor = em.find(Doctor.class, id);
		return doctor;
	}

	@Override
	public List<Doctor> readAll() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Doctor> query = em.createQuery("Select d From Doctor d", Doctor.class);
		List<Doctor> doctor = query.getResultList();
		if(doctor.size()==0)
			return null;
		else
			return doctor;
	}

	public Doctor readByFirstNameAndLastName(String doctorFirstName, String doctorLastName) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Doctor> query = em.createQuery("Select d From Doctor d where firstName=:fName and lastName=:lName", Doctor.class);
		query.setParameter("fName", doctorFirstName);
		query.setParameter("lName", doctorLastName);
		List<Doctor> doctor = query.getResultList();
		if(doctor.size()==0)
			return null;
		else
			return doctor.get(0);
	}

}
