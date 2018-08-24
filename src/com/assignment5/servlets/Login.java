package com.assignment5.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assignment5.DAO.LoginDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String pass1 = request.getParameter("pass");
		if (pass1 == null || id == null) {
			// response.sendRedirect("new_account.html");
			return;
		}
		int accno1 = Integer.parseInt(id);
		String driver = "org.apache.derby.jdbc.ClientDriver";
		String url = "jdbc:derby://localhost:1527/sample";
		String user = "user";
		String pass = "pass";
		LoginDAO login = new LoginDAO();
		PrintWriter out = response.getWriter();
		// request.getRequestDispatcher("link.html").include(request, response);

		if (login.check(pass1, accno1)) {
			out.print("Welcome, " + id);
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			response.sendRedirect("Profile");

		} else {
			out.print("Sorry, username or password error!");
			request.getRequestDispatcher("login.html").include(request, response);
		}
		out.close();
		// doGet(request, response);

	}
}
