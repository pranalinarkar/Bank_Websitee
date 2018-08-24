package com.assignment5.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assignment5.models.Account;
import com.assignment5.service.Transaction;

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/withdraw.do")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Withdraw() {
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
		doGet(request, response);
		String accno1 = request.getParameter("accno");
		String amt1 = request.getParameter("amt");
		int accno = Integer.parseInt(accno1);
		long amt = Long.parseLong(amt1);
		HttpSession session = request.getSession();
		 Object id1 = session.getAttribute("id");
		PrintWriter out = response.getWriter();
		if (accno1 == null || amt1 == null) {
			response.sendRedirect("Login.html");
			return;
		}
		if( id1==null)
		{
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Session has expired');");
			out.println("setTimeout(function(){window.location='Login.html';},1000);");
			out.println("</script>");
			return;
		}
		int id = Integer.parseInt((String)id1);
		boolean flag = false;
		Transaction account = new Transaction();
		

		flag = account.debit(accno, amt, id);
		System.out.println(flag);
		if (flag == true) {
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Processed');");
			out.println("setTimeout(function(){window.location='indexx1.html';},1000);");
			out.println("</script>");
		} else {
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Insufficient balance');");
			out.println("setTimeout(function(){window.location='withdraw.html';},1000);");

			out.println("</script>");
		}

	}

}
