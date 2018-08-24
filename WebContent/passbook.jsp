<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.assignment5.AccountDAO"%>
<%@ page import="com.assignment5.models.Payment"%>
<%@ page import="com.assignment5.models.Passbook"%>
<%@ page import="com.assignment5.PassbookDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
	<%
 Object id=session.getAttribute("id");
	if(id==null){
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Session has expired');");
		out.println("setTimeout(function(){window.location='Login.html';},1000);");
		out.println("</script>");
		return;
	}
 System.out.println(id);
      List<Passbook> accounts=PassbookDAO.getPass(Integer.parseInt((String)id));
      System.out.println(accounts.toString());
    %>
	<table style="width: 100%" class="w3-table-all w3-centered">
		<tr>

			<th>Account no</th>
			<th>Transaction</th>
			<th>Amount Paid</th>
			<th>Balance</th>
			<th>Description</th>
			<th>History</th>
		</tr>
		<%for (Passbook a: accounts){%>
		<tr>

			<td><%= a.getAccno() %></td>
			<td><%=a.getTransaction()  %></td>
			<td><%= a.getAmount() %></td>
			<td><%= a.getBalance() %></td>
			<td><%= a.getDescription() %></td>
			<td><%= a.getDate() %></td>
		</tr>
		<%} %>

	</table>
</body>
</html>