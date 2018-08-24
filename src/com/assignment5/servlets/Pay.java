package com.assignment5.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assignment5.DAO.ConnectionUtil;
import com.assignment5.DAO.PassbookDAO;
import com.assignment5.DAO.PaymentDAO;
import com.assignment5.models.Passbook;
import com.assignment5.models.Payment;
import com.assignment5.service.Transaction;

/**
 * Servlet implementation class Pay
 */
@WebServlet("/pay-bills.do")
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Double balance = 0.0;
		String type = request.getParameter("title");
		String accno1 = request.getParameter("accno");
		// String etype=request.getParameter("Electric Bill");
		// String ptype=request.getParameter("Phone Bill");
		String amount = request.getParameter("amt");
		System.out.println(type);
		double amt = Double.parseDouble(amount);
		int accno = Integer.parseInt(accno1);
		PrintWriter out=response.getWriter();
		
		String provider = null;
		HttpSession session = request.getSession();
		Object id1 =  session.getAttribute("id");
		if (type == null) {
			// response.sendRedirect("new_account.html");
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

		if (type.equals("Electric Bill")) {
			provider = (String) request.getParameter("Provider1");
		} else {
			provider = (String) request.getParameter("Provider2");
		}

		System.out.println(provider);

		
		boolean flag = false;

		Transaction account = new Transaction();
		flag = account.pay(accno, amt, id);
		if (flag == true) {
			Payment acc = new Payment(accno, type, provider, amt, id);
			PaymentDAO paydao = new PaymentDAO();
			paydao.create(acc);
			System.out.println(acc.toString());
			out.println("<h2>Payment Successful</hr>");

			String description = type + " of" + amt + " was paid to " + provider;
			Passbook passbook = new Passbook(accno, "DEBIT", amt, balance, description);
			PassbookDAO.insert(passbook, id);
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Processed');");
			out.println("setTimeout(function(){window.location='indexx1.html';},3000);");
			out.println("</script>");
		} else {
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Insufficient Balance');");
			out.println("setTimeout(function(){window.location='bill-pay.html';},1000);");
			out.println("</script>");
		}

	}

}
