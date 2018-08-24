package com.assignment5.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment5.DAO.ConnectionUtil;
import com.assignment5.DAO.UserDetailsDAO;
import com.assignment5.models.UserDetails;

/**
 * Servlet implementation class User
 */
@WebServlet("/new_user.do")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String stryr = request.getParameter("DOB");
		String pass1 = request.getParameter("pass");
		String title = request.getParameter("title");
		String pan = request.getParameter("pan");
		System.out.println(fname);
		System.out.println(lname);
		System.out.println(stryr);
		System.out.println(title);

		if (fname == null || lname == null || stryr == null || title == null || email == null || pan == null) {
			// response.sendRedirect("new_account.html");
			return;
		}

		if (fname.length() < 3 && lname.length() < 3) {
			System.out.println("Invalid name");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<h2>" + title + "\t" + fname + "\t" + lname
					+ ", Your Profile creation request is under processing.</hr>");

			UserDetails acc = new UserDetails(fname, lname, title, stryr, email, pass1, pan);
			UserDetailsDAO accdao = new UserDetailsDAO();
			accdao.create(acc);
			int id = accdao.fetch(pan);
			out.println("<h2>Your Account is created!</hr>");
			out.println("<h2>Your ID is:</hr>" + id);
			doGet(request, response);
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Created');");
			out.println("setTimeout(function(){window.location='indexx.html';},2000);");
			out.println("</script>");

		}
	}
}
