package com.fdmgroup.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="appointment")
public class Appointment implements IStorable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_appointmentid")
	@SequenceGenerator(sequenceName="seq_appointmentid", allocationSize=1, name="seq_appointmentid")
	@Column(name="appointment_id")
	private int appointmentId;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;
	
	@Temporal(TemporalType.DATE)
	@Column(name="appointmentdate")
	private Date appointmentDate;
	
	@Temporal(TemporalType.TIME)
	@Column(name="appointmenttime")
	private Date appointmentTime;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	public Appointment() {
	}

	public Appointment(int appointmentId, Doctor doctor, Date appointmentDate, Date appointmentTime, Patient patient) {
		super();
		this.appointmentId = appointmentId;
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.patient = patient;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public Appointment setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
		return this;
	}

	public Patient getPatient() {
		return patient;
	}

	public Appointment setPatient(Patient patient) {
		this.patient = patient;
		return this;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public Appointment setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
		return this;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public Appointment setDoctor(Doctor doctor) {
		this.doctor = doctor;
		return this;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public Appointment setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
		return this;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", doctorId=" + doctor + ", appointmentDate="
				+ appointmentDate + ", appointmentTime=" + appointmentTime + ", patientId=" + patient + "]";
	}

}
