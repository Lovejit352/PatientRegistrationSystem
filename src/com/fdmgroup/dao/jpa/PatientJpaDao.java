package com.fdmgroup.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.IPatientDao;
import com.fdmgroup.model.Patient;

public class PatientJpaDao implements IPatientDao{

	@Override
	public boolean create(Patient t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public boolean update(Patient t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public Patient readById(int id) {
		EntityManager em = ConnectionManager.getEntityManager();
		Patient patient = em.find(Patient.class, id);
		return patient;
	}

	@Override
	public List<Patient> readAll() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Patient> query = em.createQuery("Select p From Patient p order by p.patientId", Patient.class);
		List<Patient> patients = query.getResultList();
		if(patients.size()==0)
			return null;
		else
			return patients;
	}

	@Override
	public Patient readByEmail(String email) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Patient> query = em.createQuery("Select p From Patient p Where email = :email", Patient.class);
		query.setParameter("email", email);
		List<Patient> patient = query.getResultList();
		if(patient.size()==0)
			return null;
		else
			return patient.get(0);
	}

	public Patient readByFirstNameAndLastName(String patientFirstName, String patientLastName) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Patient> query = em.createQuery("Select p From Patient p Where firstName = :fName and lastName= :lName", Patient.class);
		query.setParameter("fName", patientFirstName);
		query.setParameter("lName", patientLastName);
		List<Patient> patient = query.getResultList();
		if(patient.size()==0)
			return null;
		else
			return patient.get(0);
	}	
}
