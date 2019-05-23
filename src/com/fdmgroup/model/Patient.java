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
@Table(name="patient")
public class Patient implements IStorable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_patientid")
	@SequenceGenerator(sequenceName="seq_patientid", allocationSize=1, name="seq_patientid")
	@Column(name="patient_id")
	private int patientId;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	private Date birthDate;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phonenumber")
	private String contactNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="reg_date")
	private Date registrationDate;
	
	@ManyToOne
	@JoinColumn(name="createdby")
	private User createdBy;
	
	public Patient() {
	}

	public Patient(int patientId, String firstName, String lastName, String email, Date birthDate, String address,
			String contactNo, Date registrationDate, User createdBy) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.address = address;
		this.contactNo = contactNo;
		this.registrationDate = registrationDate;
		this.createdBy = createdBy;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public Patient setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public int getPatientId() {
		return patientId;
	}

	public Patient setPatientId(int patientId) {
		this.patientId = patientId;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Patient setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Patient setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Patient setEmail(String email) {
		this.email = email;
		return this;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Patient setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
		return this;
	}
	
	public String getAddress() {
		return address;
	}

	public Patient setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getContactNo() {
		return contactNo;
	}

	public Patient setContactNo(String contactNo) {
		this.contactNo = contactNo;
		return this;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public Patient setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
		return this;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", birthDate=" + birthDate + ", address=" + address + ", contactNo=" + contactNo
				+ ", registrationDate=" + registrationDate + ", createdBy=" + createdBy + "]";
	}
}
