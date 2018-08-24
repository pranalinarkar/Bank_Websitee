package com.assignment5.DAO;

import java.sql.*;

public class LoginDAO {
	private static final String SQL_SELECT_ID="select pass from Userdetails where id=?";
	
public boolean check(String pass, int accno){
	try(Connection con = ConnectionUtil.getConnection()){
		PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);
		ps.setInt(1, accno);
		ResultSet rs=ps.executeQuery();
		String pass1=null;
		if(rs.next()){
			 pass1=rs.getString("pass");
			 if(pass1.equals(pass)){
				 return true;
			 }
		}
	
	}catch(SQLException ex) {
		ex.printStackTrace();
}
	return false;
}}
	

