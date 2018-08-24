package com.assignment5.models;

public class UserDetails {
	private String firstName, lastName, title, email, pass;

	private String dob;
	private String pan;

	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetails(String firstName, String lastName, String title, String dob, String email, String pass,
			String pan) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.dob = dob;
		this.email = email;
		this.pass = pass;

		this.pan = pan;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

}
