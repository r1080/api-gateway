package edu.tlabs.api.model;

public class User {

	private String userName;
	private String password;

	public User() {

	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

}
