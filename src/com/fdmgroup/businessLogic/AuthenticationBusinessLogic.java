package com.fdmgroup.businessLogic;

import com.fdmgroup.dao.jpa.UserJpaDao;
import com.fdmgroup.model.User;

public class AuthenticationBusinessLogic {

	private UserJpaDao userDao;
	
	public AuthenticationBusinessLogic() {
		userDao = new UserJpaDao();
	}

	public void setUserDao(UserJpaDao userDao) {
		this.userDao = userDao;
	}

	public User login(String username, String password) throws IllegalArgumentException{
		User user = userDao.readByUserName(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		else{
			throw new IllegalArgumentException("User was not found");
		}
	}
	
	public String changePassword(User t, String currentPassword, String newPassword, String confirmedPassword) throws IllegalArgumentException{
		String actualPassword = t.getPassword();
		if(!actualPassword.equals(currentPassword)) {
			throw new IllegalArgumentException("Wrong password");
		}
		else {
			if(newPassword.length()<4)
				throw new IllegalArgumentException("Password length must be atleast 4 characters");
			else if(!newPassword.equals(confirmedPassword)) {
				throw new IllegalArgumentException("Passwords should be same");
			}
			t.setPassword(newPassword);
			userDao.update(t);
			return null;
		}
	}

	public void changeUserPassword(int id, String newPassword, String confirmedPassword) throws IllegalArgumentException{
		User user = userDao.readById(id);
		if(user==null) {
			throw new IllegalArgumentException("User was not found");
		}
		else {
			if(newPassword.length()<4)
				throw new IllegalArgumentException("Password length must be atleast 4 characters");
			else if(!newPassword.equals(confirmedPassword)) {
				throw new IllegalArgumentException("Passwords should be same");
		}
			user.setPassword(newPassword);
			userDao.update(user);
		}
	}
	
	public void userValidations(String firstname, String lastname, String email, String contact, String address, String type) throws IllegalArgumentException{
		String errors = null;
		if(!firstname.matches("[A-Za-z]+$"))
				errors = "Invalid Characters In First Name";
		else if(!lastname.matches("[A-Za-z]+$"))
				errors = "Invalid Characters In Last Name";
		else if(!email.matches("[A-Za-z0-9]+@[A-za-z]+\\.[A-Za-z]+$"))
				errors = "Invalid Email";
		else if(contact.length()!=10)
				errors = "Contact Must Be Of 10 Digits";
		else if(!type.equals("R") && !type.equals("A"))
				errors = "Invalid User Type";
		
		if(errors!=null) {
			throw new IllegalArgumentException(errors);
		}
	}
	
	public void patientValidations(String firstname, String lastname, String email,
			String contact, String address) throws IllegalArgumentException{
		String errors = null;

		if(!firstname.matches("[A-Za-z]+$"))
				errors = "Invalid Characters In First Name";
		else if(!lastname.matches("[A-Za-z]+$"))
				errors = "Invalid Characters In Last Name";
		else if(!email.matches("[A-Za-z0-9]+@[A-za-z]+\\.[A-Za-z]+$"))
				errors = "Invalid Email";
		else if(contact.length()!=10)
				errors = "Contact Must Be Of 10 Digits";

		if(errors!=null) {
			throw new IllegalArgumentException(errors);
		}
	}
}
