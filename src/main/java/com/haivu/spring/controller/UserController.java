package com.haivu.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haivu.spring.model.User;
import com.haivu.spring.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	@Qualifier(UserService.NAME)
	private UserService userSv;

	public void setPage(Integer page, ModelMap model) {
		int currentPage = 1;
		int maxRow = 5;
		if (page != null)
			currentPage = page;

		int rowCount = userSv.getPageNumber();

		int pageSize = (int) Math.ceil(rowCount * 1.0 / maxRow);

		List<User> listUser = userSv.getAllUser(maxRow, (currentPage - 1)
				* maxRow);

		// System.out.println(currentPage);
		// System.out.println(maxRow);
		// System.out.println("Count " + rowCount);
		// System.out.println(pageSize);
		//
		// for (User u : listUser) {
		// System.out.println(u.getUserId());
		// System.out.println(u.getUserName());
		// System.out.println(u.getPwd());
		// System.out.println(u.getFullName());
		// System.out.println(u.getBirthOfDay());
		// System.out.println(u.getEmail());
		// System.out.println(u.isActive());
		// }

		model.put("listUser", listUser);
		model.put("pageSize", pageSize);
		model.put("currentPage", currentPage);
	}

	@RequestMapping(value = "users-list", method = RequestMethod.GET)
	public String getListUser(
			@RequestParam(value = "page", required = false) Integer page,
			ModelMap model) {

		this.setPage(page, model);

		return "users-list";
	}

	@RequestMapping(value = "add-user", method = RequestMethod.GET)
	public String addNewUser(
			@RequestParam(value = "page", required = true) Integer page,
			ModelMap model, User user) {
		model.put("edit", 0);
		model.put("page", page);

		return "add-edit-user";
	}

	@RequestMapping(value = "edit-user", method = RequestMethod.GET)
	public String editUser(
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "userId", required = true) Integer userId,
			ModelMap model, User user) {

		user = userSv.getUserById(userId);
		model.put("user", user);
		model.put("edit", 1);
		model.put("page", page);

		return "add-edit-user";
	}

	@RequestMapping(value = "save-user", method = RequestMethod.POST)
	public String saveUser(
			@RequestParam(value = "page", required = false) Integer page,
			@ModelAttribute(value = "user") User user) {
		if (user.getUserId() != 0) {
			userSv.editUser(user);
			return "redirect:users-list?page=" + page;
		} else {
			userSv.addNewUser(user);
			return "redirect:users-list";
		}
	}

	@RequestMapping(value = "del-user/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody String delUser(@PathVariable Integer userId) {
		if (userSv.delUser(userId)) {
			return "Delete user successfully";
		} else {
			return "Delete user fail";
		}
	}

	@RequestMapping(value = "update-status/{userId}/{checked}", method = RequestMethod.POST)
	public @ResponseBody String updateStatus(@PathVariable Integer userId,
			@PathVariable boolean checked) {
		if (userSv.updateStatus(userId, checked)) {
			return "Update status user successfully";
		} else {
			return "Update status user fail";
		}
	}

}
