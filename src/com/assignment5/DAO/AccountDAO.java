package com.assignment5.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.assignment5.models.Account;
import com.assignment5.models.Payment;

public class AccountDAO implements DAO<Account> {
	private static final String SQL_INSERT = "insert into Account (pin,id,balance,type)values(?,?,?,?)";
	private static final String SQL_SELECT = "select accno from Account where pin=?";
	private static final String SQL = "select * from account where id=?";
	private static final String SQL_bill = "select * from payment where accno=?";
	private static final String SQL_SELECT_ID = "select balance from Account where accno=? and id=?";
	private static final String SQL_UPDATE = "update account set balance = ? where  accno=? and id=?";

	@Override
	public void create(Account object) {
		// TODO Auto-generated method stub
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);

			ps.setInt(1, object.getPin());
			ps.setInt(2, object.getId());

			ps.setDouble(3, object.getBalance());
			ps.setString(4, object.getType());

			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int fetch(int pin) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ps.setInt(1, pin);
			ResultSet rs = ps.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));
			int accno = rs.getInt(1);
			return accno;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static String fetchBalance(int accno, int id) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);
			ps.setInt(1, accno);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			String balance1 = null;
			if (rs.next()) {
				balance1 = rs.getString("balance");
			}
			System.out.println(balance1);
			return balance1;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void updateBalance(int id, int accno, double balance) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_UPDATE);
			ps.setDouble(1, balance);
			ps.setInt(2, accno);
			ps.setInt(3, id);
			ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
	}

	public static List<Account> getAccount(int id) {
		List<Account> accounts = new ArrayList<>();
		try {

			Connection con = ConnectionUtil.getConnection();
			// Statement statement = con.createStatement() ;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setAccno(rs.getInt("accno"));
				account.setPin(rs.getInt("pin"));
				account.setId(rs.getInt("id"));
				account.setBalance(rs.getDouble("balance"));
				account.setType(rs.getString("type"));
				accounts.add(account);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public static List<Payment> getBill(int id) {
		List<Account> accounts = new ArrayList<>();
		List<Payment> bill = new ArrayList<>();
		try {

			Connection con = ConnectionUtil.getConnection();
			// Statement statement = con.createStatement() ;
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setAccno(rs.getInt("accno"));

				accounts.add(account);
			}
			Connection con1 = ConnectionUtil.getConnection();
			// Statement statement = con.createStatement() ;
			PreparedStatement ps1 = con1.prepareStatement(SQL_bill);
			for (Account a : accounts) {
				ps1.setInt(1, a.getAccno());
				ResultSet rs1 = ps1.executeQuery();
				while (rs1.next()) {
					Payment pay = new Payment();
					pay.setAccno(rs1.getInt("accno"));
					pay.setType(rs1.getString("type"));
					pay.setProvider(rs1.getString("provider"));
					pay.setAmt(rs1.getDouble("amount"));

					bill.add(pay);
				}

			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return bill;
	}

}
     