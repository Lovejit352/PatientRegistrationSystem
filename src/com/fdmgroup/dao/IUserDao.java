package com.fdmgroup.dao;

import com.fdmgroup.model.User;

public interface IUserDao extends ICreatable<User>, IReadable<User>, IUpdatable<User>{

	User readByUserName(String username);
	User readByEmail(String email);
	boolean resetPassword(User user);
}
