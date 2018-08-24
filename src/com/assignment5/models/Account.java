package com.assignment5.models;

public class Account {

	private double balance;
	private int id;
	private int pin;
	private int accno;
	private String type;

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int pin, int id, double balance, String type) {
		super();

		this.balance = balance;
		this.pin = pin;
		this.id = id;
		this.type = type;

	}



public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public int getPin() {
	return pin;
}
public void setPin(int pin) {
	this.pin = pin;
}
public void setId(int id) {
	this.id = id;
}
public int getId() {
	return id;
}


}
