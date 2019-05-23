package com.fdmgroup.dao;

import java.util.List;

import com.fdmgroup.model.Appointment;

public interface IAppointmentDao extends ICreatable<Appointment>, IReadable<Appointment>, IUpdatable<Appointment>{
	List<Appointment> readByPatientId(int id);
}
