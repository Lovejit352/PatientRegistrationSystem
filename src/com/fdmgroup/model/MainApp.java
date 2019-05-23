package com.fdmgroup.model;

import com.fdmgroup.dao.jpa.ConnectionManager;

public class MainApp {
	public static void main(String[] args) {
		
		ConnectionManager.getEntityManager();
		
	}
}
