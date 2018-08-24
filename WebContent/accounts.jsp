<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.assignment5.AccountDAO"%>
<%@ page import="com.assignment5.models.Account"%>

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
      List<Account> accounts=AccountDAO.getAccount(Integer.parseInt((String)id));
    %>
	<table style="width: 100%" class="w3-table-all w3-centered">
		<tr>
			<th>ID</th>
			<th>Account no</th>
			<th>Pin</th>
			<th>Balance</th>
			<th>Type</th>
		</tr>
		<%for (Account a: accounts){%>
		<tr>
			<td><%= a.getId() %></td>
			<td><%= a.getAccno() %></td>
			<td><%=a.getPin()  %></td>
			<td><%= a.getBalance() %></td>
			<td><%= a.getType() %></td>
		</tr>
		<%} %>

	</table>
</body>
</html>