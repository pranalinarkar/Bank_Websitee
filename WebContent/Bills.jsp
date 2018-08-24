<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.assignment5.AccountDAO"%>
<%@ page import="com.assignment5.models.Payment"%>

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
      List<Payment> accounts=AccountDAO.getBill(Integer.parseInt((String)id));
      System.out.println(accounts.toString());
    %>
	<table style="width: 100%" class="w3-table-all w3-centered">
		<tr>

			<th>Account no</th>
			<th>Type</th>
			<th>Provider</th>
			<th>Amount Paid</th>
		</tr>
		<%for (Payment a: accounts){%>
		<tr>

			<td><%= a.getAccno() %></td>
			<td><%=a.getType()  %></td>
			<td><%= a.getProvider() %></td>
			<td><%= a.getAmt() %></td>
		</tr>
		<%} %>

	</table>
</body>
</html>