package com.assignment5.models;

import java.sql.Timestamp;

public class Passbook {
	private int accno;
	private String transaction;
	private double amount;
	private double balance;
	private String description;
	private Timestamp date;


	public Passbook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passbook(int accno, String transaction, double amount, double balance, String description) {
		super();
		this.accno = accno;
		this.transaction = transaction;
		this.amount = amount;
		this.balance = balance;
		this.description = description;

	}

	public int getAccno() {
		return accno;
	}

	public String getTransaction() {
		return transaction;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}

	public String getDescription() {
		return description;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp timestamp) {
		this.date = timestamp;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	

}
