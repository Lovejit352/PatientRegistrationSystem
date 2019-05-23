package com.fdmgroup.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.User;

public class UserJpaDao implements IUserDao{
	
	@Override
	public boolean create(User t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public User readById(int id) {
		EntityManager em = ConnectionManager.getEntityManager();
		User user = em.find(User.class, id);
		if(user==null)
			return null;
		else
			return user;
	}

	@Override
	public List<User> readAll() {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<User> query = em.createQuery("Select u From User u order by u.userId", User.class);
		List<User> user = query.getResultList();
		em.close();
		if(user.size()==0)
			return null;
		else
			return user;
	}

	@Override
	public boolean update(User t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	@Override
	public User readByEmail(String email) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE email = :email", User.class);
		query.setParameter("email", email);
		List<User> user = query.getResultList();
		em.close();
		if(user.size()==0)
			return null;
		else
			return user.get(0);
	}

	@Override
	public User readByUserName(String username) {
		EntityManager em = ConnectionManager.getEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE username = :username", User.class);
		query.setParameter("username", username);
		List<User> user = query.getResultList();
		em.close();
		if(user.size()==0)
			return null;
		return user.get(0);
	}
	
	public boolean resetPassword(User t) {
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}
}
