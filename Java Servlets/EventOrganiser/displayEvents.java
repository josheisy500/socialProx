
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

/**
 * Servlet implementation class displayEvents
 */
@WebServlet("/displayEvents")
public class displayEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/TEST";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";

	Connection conn;
	Statement stmt;
	
	

	public displayEvents() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		if (session != null) {
			String login = (String) session.getAttribute("login");

			displaySportEvents(login, out);
		}

		else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Woah! Please Login First');");
			out.println("</script>");
			request.getRequestDispatcher("eventOrg_Login.html").include(
					request, response);
		}
		out.close();

	}

	

	private void displaySportEvents(String login, PrintWriter out) {
		try {
			
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			//String sql = "SELECT * FROM sports_events WHERE createdBy=?";
			PreparedStatement ps = conn
					.prepareStatement("SELECT * from sports_events WHERE createdBy=?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			
			
			//html 
			String docType =
					"<!doctype html public \"-//w3c//dtd html 4.0 " +
					"transitional//en\">\n";
					out.println(docType +
							"<html>\n" +
							"<a href=MainMenu.html>Main Menu</a>"+
							"<head>\n"+
							"<link rel=stylesheet href=http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css>"+
							"<div class=jumbo>"+
							
							
							"<style type=text/css>" +
							".tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}" +
							".tftable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}"+
							".tftable tr {background-color:#d4e3e5;}"+
							".tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}"+
							".tftable tr:hover {background-color:#ffffff;}"+
							"</style>"+
							
							
							"<div align=center style=color: #FF0000>"+
							"<h1 align=\"center\">Welcome " + login + "<br> Here Are Your Created Events</br></h1>\n" +
							"<table class=tftable  align=\"center\">\n" +
							 "<th>Sports Events</th>\n" +
							 "<tr>\n" +
				             "<th>Name of Event</th><th>Longitude</th><th>Latitude</th><th>Date</th>"
				             + "</tr>\n" +
							"</body></html>");
			while(rs.next()){
				String docType1 =
				"<!doctype html public \"-//w3c//dtd html 4.0 " +
				"transitional//en\">\n";
				out.println(docType1 +
						"<html>\n" +
			             "<tr>\n" +
			             "<td>" + rs.getString(2) + "</td>\n" +  
			             "<td>" + rs.getString(3) + "</td>\n" +
			             "<td>" + rs.getString(4) + "</td>\n" +
			             "<td>" + rs.getString(5) + "</td></tr>\n" +
			       
						"</body></html>");
				
			}//end while
			displayMusicEvents(login, out, conn);
			
			
		}catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		} // end try
			
	}

	private void displayMusicEvents(String login, PrintWriter out, Connection conn2) {
		try {
			
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//use same connection 
			conn = conn2;
			
			PreparedStatement ps = conn
					.prepareStatement("SELECT * from music_events WHERE createdBy=?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			
			//html 
			String docType =
					"<!doctype html public \"-//w3c//dtd html 4.0 " +
					"transitional//en\">\n";
					out.println(docType +
							"<html>\n" +
							"<style type=text/css>" +
							".tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}" +
							".tftable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}"+
							".tftable tr {background-color:#d4e3e5;}"+
							".tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}"+
							".tftable tr:hover {background-color:#ffffff;}"+
							"</style>"+
							
							"<table class=tftable  align=\"center\">\n" +
							 
							"<th>Music Events</th></br>" +
							 "<tr>\n" +
				             "<th>Name of Event</th><th>Longitude</th><th>Latitude</th><th>Date</th>"
				             + "</tr>\n" +
							"</body></html>");
			while(rs.next()){
				String docType1 =
				"<!doctype html public \"-//w3c//dtd html 4.0 " +
				"transitional//en\">\n";
				out.println(docType1 +
						"<html>\n" +
						
			             "<tr>\n" +
			             "<td>" + rs.getString(2) + "</td>\n" +  
			             "<td>" + rs.getString(3) + "</td>\n" +
			             "<td>" + rs.getString(4) + "</td>\n" +
			             "<td>" + rs.getString(5) + "</td></tr>\n" +
						"</body></html>");
			}//end while
			//invoke function to display music events 
			displayFDevents(login, out, conn);
			
			
			
		}catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		} // end try
		
	}
	
	private void displayFDevents(String login, PrintWriter out, Connection conn2) {
		try {
			
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			
			conn = conn2;
			
			PreparedStatement ps = conn
					.prepareStatement("SELECT * from food_drink_events WHERE createdBy=?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			
			//html 
			String docType =
					"<!doctype html public \"-//w3c//dtd html 4.0 " +
					"transitional//en\">\n";
					out.println(docType +
							"<html>\n" +
							"<style type=text/css>" +
							".tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}" +
							".tftable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}"+
							".tftable tr {background-color:#d4e3e5;}"+
							".tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}"+
							".tftable tr:hover {background-color:#ffffff;}"+
							"</style>"+
							
							"<table class=tftable  align=\"center\">\n" +
							 
							 "<th>Food & Drink Events</th></br>" +
							 "<tr>\n" +
				             "<th>Name of Event</th><th>Longitude</th><th>Latitude</th><th>Date</th>"
				             + "</tr>\n" +
							"</body></html>");
			while(rs.next()){
				String docType1 =
				"<!doctype html public \"-//w3c//dtd html 4.0 " +
				"transitional//en\">\n";
				out.println(docType1 +
						"<html>\n" +
						
			             "<tr>\n" +
			             "<td>" + rs.getString(2) + "</td>\n" +  
			             "<td>" + rs.getString(3) + "</td>\n" +
			             "<td>" + rs.getString(4) + "</td>\n" +
			             "<td>" + rs.getString(5) + "</td></tr>\n" +
						"</body></html>");
			}//end while
			
			
			
		}catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		} // end try	
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
