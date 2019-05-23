package com.fdmgroup.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.IAppointmentDao;
import com.fdmgroup.model.Appointment;

public class AppointmentJpaDao implements IAppointmentDao{

	@Override
	public boolean create(Appointment t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public Appointment readById(int id) {
		EntityManager em = ConnectionManager.getEntityManager();
		Appointment appointment = em.find(Appointment.class, id);
		return appointment;
	}

	@Override
	public List<Appointment> readAll() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Appointment> query = em.createQuery("Select a From Appointment a order by a.appointmentId", Appointment.class);
		List<Appointment> appointment = query.getResultList();
		if(appointment.size()==0)
			return null;
		else
			return appointment;
	}

	@Override
	public boolean update(Appointment t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public List<Appointment> readByPatientId(int id) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Appointment> query = em.createQuery("Select a From Appointment a Where patient_id = :patId", Appointment.class);
		query.setParameter("patId", id);
		List<Appointment> appointment = query.getResultList();
		if(appointment.size()==0)
			return null;
		else
			return appointment;
	}

	public List<Appointment> getAppointmentsByDoctorAndDateAndTime(Date appDate, Date appTime, int doc_id) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Appointment> query = em.createQuery("Select a From Appointment a Where doctor_id = :doc_id And appointmentdate = :appDate And appointmenttime = :appTime", Appointment.class);
		query.setParameter("doc_id", doc_id);
		query.setParameter("appDate", appDate);
		query.setParameter("appTime", appTime);
		List<Appointment> appointment = query.getResultList();
		if(appointment.size()==0)
			return null;
		else
			return appointment;
	}

	public List<Appointment> getAppointmentsByPatientAndDateAndTime(int pat_id, Date appDate, Date appTime) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Appointment> query = em.createQuery("Select a From Appointment a Where patient_id = :pat_id And appointmentdate = :appDate And appointmenttime = :appTime", Appointment.class);
		query.setParameter("pat_id", pat_id);
		query.setParameter("appDate", appDate);
		query.setParameter("appTime", appTime);
		List<Appointment> appointment = query.getResultList();
		if(appointment.size()==0)
			return null;
		else
			return appointment;
	}

	public List<Appointment> readUpcomingAppointments() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Appointment> query = em.createQuery("Select a From Appointment a Where appointmentDate BETWEEN SYSDATE AND SYSDATE+35 order by a.appointmentId", Appointment.class);
		List<Appointment> appointment = query.getResultList();
		if(appointment.size()==0)
			return null;
		else
			return appointment;
	}
}
