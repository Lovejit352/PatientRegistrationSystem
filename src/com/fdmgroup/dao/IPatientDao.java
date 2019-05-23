package com.fdmgroup.dao;

import com.fdmgroup.model.Patient;

public interface IPatientDao extends ICreatable<Patient>, IUpdatable<Patient>, IReadable<Patient>{
	Patient readByEmail(String email);
}
