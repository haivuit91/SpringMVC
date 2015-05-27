package com.haivu.spring.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	private int userId;
	private String userName;
	private String pwd;
	private String fullName;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateOfBirth;
	private String email;
	private boolean isActive;

	public User() {
	}

	public User(int userId, String userName, String pwd, String fullName,
			Date dateOfBirth, String email, boolean isActive) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.pwd = pwd;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.isActive = isActive;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
