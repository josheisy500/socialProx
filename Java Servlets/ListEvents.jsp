<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import= "java.sql.PreparedStatement" %>
<%@ page import= "java.sql.Connection" %>
<%@ page import= "java.sql.PreparedStatement" %>
<%@ page import= "java.sql.Statement" %>
<%@ page import= "javax.servlet.http.HttpSession" %>
<%@ page import= "java.sql.ResultSet" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   


<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<title>Your Events</title>


<style type="text/css">
.tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}
.tftable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}
.tftable tr {background-color:#d4e3e5;}
.tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}
.tftable tr:hover {background-color:#ffffff;}
</style>



<div class="jumbo">

</head>
<body>

<%
	//diplay error message if no user is logged in 
	if(session.getAttribute("login") == null){
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('Woah! Please Login First');");  
		out.println("</script>");
	
	}


%>

<body background="bg.jpg">
	<sql:setDataSource
		var="myDS"
		driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/test"
		user="root" password="password"
	/>
	

	<!-- query for sport events -->
	<sql:query var="listSports_events"	dataSource="${myDS}" > 
		SELECT * FROM sports_events WHERE createdBy="<%=session.getAttribute("login")%>";
	</sql:query>
	
	<!-- query for food_drink events -->
	<sql:query var="list_food_drink_events"	dataSource="${myDS}" > 
		SELECT * FROM food_drink_events WHERE createdBy="<%=session.getAttribute("login")%>";
	</sql:query>
	
	<!-- query for music events -->
	<sql:query var="listMusic_events"	dataSource="${myDS}" > 
		SELECT * FROM music_events WHERE createdBy="<%=session.getAttribute("login")%>";
	</sql:query>
	
	
	
	<div align="center" style="color: #FF0000">
		
		<h1><%=session.getAttribute("login")%></h1>
		<a href="MainMenu.html">Main Menu</a><br/>
		<br>
		<!-- Table for sport events -->
		<table class="tftable" border="1" cellpadding="5" >
			<style="color: #FF0000" caption><h2>Sports Events</h2></caption>
			<tr style="color: #FF0000">
				<th>Name of Event</th>
				<th>Longitude </th>
				<th>Latitude </th>
				<th>Date </th>
				<th>Event Photo</th>

			</tr>
			
			<c:forEach var="sports_events" items="${listSports_events.rows}">
				<tr>
					<td><c:out value="${sports_events.name}" /></td>
					<td><c:out value="${sports_events.longitude}" /></td>
					<td><c:out value="${sports_events.latitude}" /></td>
					<td><c:out value="${sports_events.date}" /></td>
					<td><c:out value="${sports_events.event_photo}" /></td>
				</tr>
			</c:forEach>
		</table><br />
		
		
		<!-- Table for food_drink events -->
		<table class="tftable" border="1" cellpadding="5" >
			<style="color: #FF0000" caption><h2>Food and Drink Events </h2></caption>
			<tr style="color: #FF0000">
				<th>Name of Event</th>
				<th>Longitude </th>
				<th>Latitude </th>
				<th>Date </th>
				<th>Event Photo</th>

			</tr>
			
			<c:forEach var="food_drinks_events" items="${list_food_drink_events.rows}">
				<tr>
					<td><c:out value="${food_drinks_events.name}" /></td>
					<td><c:out value="${food_drinks_events.longitude}" /></td>
					<td><c:out value="${food_drinks_events.latitude}" /></td>
					<td><c:out value="${food_drinks_events.date}" /></td>
					<td><c:out value="${food_drinks_events.event_photo}" /></td>
				</tr>
			</c:forEach>
		</table><br />
		
		
		<!-- Table for music events -->
		<table class="tftable" border="1" cellpadding="5" >
			<style="color: #FF0000" caption><h2>Music Events</h2></caption>
			<tr style="color: #FF0000">
				<th>Name of Event</th>
				<th>Longitude </th>
				<th>Latitude </th>
				<th>Date </th>
				<th>Event Photo</th>

			</tr>
			
			<c:forEach var="music_events" items="${listMusic_events.rows}">
				<tr>
					<td><c:out value="${music_events.name}" /></td>
					<td><c:out value="${music_events.longitude}" /></td>
					<td><c:out value="${music_events.latitude}" /></td>
					<td><c:out value="${music_events.date}" /></td>
					<td><c:out value="${music_events.event_photo}" /></td>
				</tr>
			</c:forEach>
		</table><br />
		
		
	</div>
</body>
</html>
