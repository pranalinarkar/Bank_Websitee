package com.assignment5.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment5.models.Passbook;

public class PassbookDAO {
	private static final String SQL_INSERT = "insert into Passbook(id,accno,User_Transaction,Amount,Balance,Description) values(?,?,?,?,?,?)";
	private static final String SQL_bill = "select * from passbook where id=?";
	
	public static void insert(Passbook object, int id) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);

			ps.setInt(1, id);
			ps.setInt(2, object.getAccno());
			ps.setString(3, object.getTransaction());
			ps.setDouble(4, object.getAmount());
			ps.setDouble(5, object.getBalance());
			ps.setString(6, object.getDescription());

			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public static List<Passbook> getPass(int id) {
		List<Passbook> accounts = null;
		try {

			Connection con = ConnectionUtil.getConnection();
			// Statement statement = con.createStatement() ;
			PreparedStatement ps = con.prepareStatement(SQL_bill);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			accounts=new ArrayList<>();
			while (rs.next()) {
				Passbook account = new Passbook();
				account.setAccno(rs.getInt("accno"));

				account.setTransaction(rs.getString("user_transaction"));
				account.setAmount(rs.getDouble("amount"));
				account.setBalance(rs.getDouble("balance"));
				account.setDescription(rs.getString("description"));
				account.setDate(rs.getTimestamp("time"));
				accounts.add(account);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
}
