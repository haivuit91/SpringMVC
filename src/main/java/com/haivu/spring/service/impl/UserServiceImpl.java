package com.haivu.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haivu.spring.dao.UserDao;
import com.haivu.spring.model.User;
import com.haivu.spring.service.UserService;

@Service(UserService.NAME)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getAllUser(int limit, int offset) {
		return userDao.getAllUser(limit, offset);
	}

	@Override
	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public boolean addNewUser(User user) {
		return userDao.addNewUser(user);
	}

	@Override
	public boolean editUser(User user) {
		return userDao.editUser(user);
	}

	@Override
	public boolean delUser(int userId) {
		return userDao.delUser(userId);
	}

	@Override
	public int getPageNumber() {
		return userDao.getPageNumber();
	}

}
