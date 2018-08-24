package com.assignment5.models;

public class Payment {
	private int accno;
	private String type, provider;
	private double amt;
	private int id;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int accno, String type, String provider, double amt, int id) {
		super();
		this.accno = accno;
		this.type = type;
		this.provider = provider;
		this.amt = amt;
	}

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Payment [accno=" + accno + ", type=" + type + ", provider=" + provider + ", amt=" + amt + ", id=" + id
				+ "]";
	}

}
