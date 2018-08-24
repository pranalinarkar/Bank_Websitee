package com.assignment5.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.assignment5.DAO.AccountDAO;
import com.assignment5.DAO.ConnectionUtil;
import com.assignment5.DAO.PassbookDAO;
import com.assignment5.DAO.PaymentDAO;
import com.assignment5.models.Passbook;
import com.assignment5.models.Payment;

public class Transaction {

	public boolean debit(int accno, long amount, int id) {

		Double balance = 0.0;
		String balance1 = AccountDAO.fetchBalance(accno, id);
		System.out.println(balance1);
		
		if (balance1==null) {
			return false;	
		}
		System.out.println(balance1);
		
		balance = Double.parseDouble(balance1);
		if (balance >= amount) {
			balance -= amount;
			System.out.println("Balance after withdraw" + balance);
			AccountDAO.updateBalance(id, accno, balance);
			String description = "Amount of " + amount + " was deducted from Account Number: " + accno;
			Passbook pass = new Passbook(accno, "DEBIT", amount, balance, description);

			PassbookDAO.insert(pass, id);
			return true;
		} else {
			return false;
		}

	}

	public boolean credit(int accno, long amount, int id) {
		Double balance = 0.0;
		String balance1 = AccountDAO.fetchBalance(accno, id);
		System.out.println(balance1);
		if (balance1.equals(null)) {
			return false;
		}
		balance = Double.parseDouble(balance1);

		balance += amount;
		System.out.println("Balance after deposit" + balance);
		AccountDAO.updateBalance(id, accno, balance);
		String description = "Amount of " + amount + " was added to Account Number: " + accno;
		Passbook pass = new Passbook(accno, "CREDIT", amount, balance, description);
		PassbookDAO.insert(pass, id);
		return true;

	}

	public boolean pay(int accno, double amount, int id) {
		try (Connection con = ConnectionUtil.getConnection()) {

			Double balance = 0.0;
			// st.execute("CREATE TABLE Payment(accno int references
			// Account(accno),Type varchar(20),Provider varchar(20) ,amount
			// numeric)");
			String balance1 = AccountDAO.fetchBalance(accno, id);
			if (balance1 == null) {
				return false;
			}
			balance = Double.parseDouble(balance1);
			if (balance >= amount) {
				balance -= amount;
				AccountDAO.updateBalance(id, accno, balance);

				return true;
			} else {
				return false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}

class InvalidAcc extends Exception{
	   int a;
	   InvalidAcc(int b) {
	     a=b;
	   }
	   public String toString(){
	     return ("Invalid Account Number =  "+a) ;
	  }
	}