package com.fdmgroup.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionManager {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
