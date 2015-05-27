package com.haivu.spring.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.haivu.spring.model.User;
import com.haivu.spring.service.UserService;

@Controller
public class Test {

	@Autowired
	@Qualifier(UserService.NAME)
	private UserService userSv;

	public void getListUser(int pageSize, int pageNumber) {
		List<User> listUser = userSv.getAllUser(pageSize, pageNumber);
		for (User u : listUser) {
			System.out.println(u.getUserId());
			System.out.println(u.getUserName());
			System.out.println(u.getPwd());
			System.out.println(u.getFullName());
			System.out.println(u.getDateOfBirth());
			System.out.println(u.getEmail());
			System.out.println(u.isActive());
		}
	}

	public static void main(String[] arg) {
		Test test = new Test();
		test.getListUser(1, 0);
	}

}
