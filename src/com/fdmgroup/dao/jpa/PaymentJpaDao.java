package com.fdmgroup.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.IPaymentDao;
import com.fdmgroup.model.Payment;

public class PaymentJpaDao implements IPaymentDao{
	@Override
	public boolean create(Payment t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public Payment readById(int id) {
		EntityManager em = ConnectionManager.getEntityManager();
		Payment payment = em.find(Payment.class, id);
		return payment;
	}

	@Override
	public List<Payment> readAll() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Payment> query = em.createQuery("Select p From Payment p", Payment.class);
		List<Payment> payment = query.getResultList();
		if(payment.size()==0)
			return null;
		else
			return payment;
	}

	@Override
	public boolean update(Payment t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public Payment getPaymentByAppointmentId(int appointment_id) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<Payment> query = em.createQuery("Select p From Payment p Where appointment_id = :appId", Payment.class);
		query.setParameter("appId", appointment_id);
		List<Payment> payment = query.getResultList();
		if(payment.size()==0)
			return null;
		else
			return payment.get(0);
	}
}
