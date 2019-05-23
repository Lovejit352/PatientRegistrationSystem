package com.fdmgroup.model;

public enum UserType {
	A("Admin", 0), 
	R("Receptionist", 1);
	
	private String description;
	private int typeId;
	
	private UserType(String description, int typeId) {
		this.description = description;
		this.typeId = typeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}
