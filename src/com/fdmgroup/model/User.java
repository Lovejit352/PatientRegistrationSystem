package com.fdmgroup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fdmgroup.model.IStorable;;

@Entity
@Table(name="Users")
public class User implements IStorable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_userid")
	@SequenceGenerator(sequenceName="seq_userid", allocationSize=1, name="seq_userid")
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="phonenumber")
	private String contactNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name="createdby")
	private User createdBy;
	
	@Enumerated(EnumType.ORDINAL)
	private UserType userType;
	
	public User() {
	}
	public User(int userId, String userName, String password, String firstName, String lastName, String contactNo,
			String email, String address, User createdBy, UserType userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.email = email;
		this.address = address;
		this.createdBy = createdBy;
		this.userType = userType;
	}
	public int getUserId() {
		return userId;
	}
	public User setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public User setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getFirstName() {
		return firstName;
	}
	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public String getLastName() {
		return lastName;
	}
	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	public String getContactNo() {
		return contactNo;
	}
	public User setContactNo(String contactNo) {
		this.contactNo = contactNo;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getAddress() {
		return address;
	}
	public User setAddress(String address) {
		this.address = address;
		return this;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public User setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
		return this;
	}
	public UserType getUserType() {
		return userType;
	}
	public User setUserType(UserType userType) {
		this.userType = userType;
		return this;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", contactNo=" + contactNo + ", email=" + email + ", address="
				+ address + ", createdBy=" + createdBy.getUserName() + ", userType=" + userType + "]";
	}
}
