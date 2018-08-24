package com.assignment5.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assignment5.models.Account;
import com.assignment5.service.Transaction;

/**
 * Servlet implementation class Transfer
 */
@WebServlet("/transfer.do")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Transfer() {
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
		doGet(request, response);
		String accno1 = request.getParameter("accno1");
		String accno2 = request.getParameter("accno2");
		String amt1 = request.getParameter("amt");
		int accno = Integer.parseInt(accno1);
		int accnod = Integer.parseInt(accno2);
		int amt = Integer.parseInt(amt1);
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();

		 Object id1 = session.getAttribute("id");
		if (accno1 == null || amt1 == null || accno2 == null) {
			response.sendRedirect("index.html");
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
		boolean flag = false;
		Transaction account = new Transaction();
		
		int id = Integer.parseInt((String)id1);
		flag = account.debit(accno, amt, id);
		if (flag == true) {
			flag = account.credit(accnod, amt, id);
			if (flag == true) {
				response.setContentType("text/html");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Processed');");
				out.println("setTimeout(function(){window.location='indexx1.html';},1000);");
				out.println("</script>");
			} else {
				account.credit(accno, amt, id);
				response.setContentType("text/html");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Invalid accno');");
				out.println("setTimeout(function(){window.location='transfer.html';},1000);");
				out.println("</script>");
			}
		} else {
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Insufficient amount or invalid source account number');");
			out.println("setTimeout(function(){window.location='transfer.html';},1000);");
			out.println("</script>");
		}
	}

}
