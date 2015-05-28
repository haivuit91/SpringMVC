package com.haivu.spring.service;

import java.util.List;

import com.haivu.spring.model.User;

public interface UserService {

	public static final String NAME = "userService";

	List<User> getAllUser(int limit, int offset);

	User getUserById(int userId);

	boolean addNewUser(User user);

	boolean editUser(User user);

	boolean delUser(int userId);

	int getPageNumber();
	
	boolean updateStatus(int userId, boolean checked);

}
