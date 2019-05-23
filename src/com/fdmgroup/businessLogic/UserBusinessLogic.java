package com.fdmgroup.businessLogic;

import java.util.List;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.jpa.UserJpaDao;
import com.fdmgroup.model.User;
import com.fdmgroup.model.UserType;

public class UserBusinessLogic {
	private IUserDao userDao;

	public UserBusinessLogic() {
		userDao = new UserJpaDao();
	}

	public void register(String username, String password, String firstname, String lastname, String email,
			String contact, String address, String type, User userLogged) throws IllegalArgumentException{
		String error = null;
		if(username.length()<4)
			error="Username Must Be Atleast 4 Characters";

		else if(password.length()<4)
			error="Password Must Be Atleast 4 Characters";
		
		List<User> users = userDao.readAll();
		for (User user : users) {
			if(user.getUserName().toLowerCase().equals(username.toLowerCase()))
				error = "Username Is Already Taken";
			else if(user.getEmail().toLowerCase().equals(email.toLowerCase()))
				error = "Email Already Exists";
		}
		
		if(error!=null)
			throw new IllegalArgumentException(error);
		
		UserType userType = UserType.valueOf(type);
		User user = new User().setUserName(username).setPassword(password).setFirstName(firstname)
				.setLastName(lastname).setEmail(email).setContactNo(contact).setAddress(address).setUserType(userType)
				.setCreatedBy(userLogged);
		userDao.create(user);
	}

	public void update(User user, int user_id, String firstname, String lastname, String email, String contact, String address,
			String type) throws IllegalArgumentException{
		List<User> users = userDao.readAll();
		for (User userList : users) {
			if(userList.getEmail().toLowerCase().equals(email.toLowerCase()) && !userList.getUserName().equals(user.getUserName()))
				throw new IllegalArgumentException("Email Already Exists");
		}
		
		UserType userType = UserType.valueOf(type);
		user.setUserId(user_id).setFirstName(firstname).setLastName(lastname).setEmail(email)
				.setContactNo(contact).setAddress(address).setUserType(userType);
		userDao.update(user);
	}
	
	public User readById(int user_id) {
		return userDao.readById(user_id);
	}

	public List<User> displayAllUsers() {
		List<User> users = userDao.readAll();
		if(users==null) {
			return null;
		}
		else {
			return users;
		}
	}
	
}
