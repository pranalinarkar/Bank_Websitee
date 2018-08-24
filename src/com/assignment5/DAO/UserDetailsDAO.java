package com.assignment5.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.assignment5.models.UserDetails;

public class UserDetailsDAO implements DAO<UserDetails> {
	private static final String SQL_INSERT = "insert into Userdetails(firstName,lastName,title,dob,email,pass,pan) values(?,?,?,?,?,?,?)";
	private static final String SQL_SELECT = "select id from Userdetails where pan=?";

	@Override
	public void create(UserDetails object) {
		// TODO Auto-generated method stub
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);

			ps.setString(1, object.getFirstName());
			ps.setString(2, object.getLastName());
			ps.setString(3, object.getTitle());
			ps.setString(4, object.getDob());
			ps.setString(5, object.getEmail());
			ps.setString(6, object.getPass());

			ps.setString(7, object.getPan());
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int fetch(String pan) {
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ps.setString(1, pan);
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

}
