<<<<<<< HEAD
package com.joshua.databaseAccess;

=======
>>>>>>> 3f50457aafb162ce61ef21c303e00c44b24cb4a5
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class event_Org_Reg
 */
@WebServlet("/eventOrg_Register")
public class event_Org_Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/TEST";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";

	Connection conn;
	Statement stmt;

	public event_Org_Reg() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}// end doGet

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String login = request.getParameter("login");
		String password = request.getParameter("pass");
		String email = request.getParameter("email");

		if (userNameExists(login)) {
			// match - tell user user name already exists

			out.println("<script type=\"text/javascript\">");
			out.println("alert('Sorry User Name Exists, Redirecting...');");
			out.println("</script>");

			RequestDispatcher rs = request
					.getRequestDispatcher("eventUserReg.html");
			rs.include(request, response);
		} else {
			addUser(login, password, email);

			out.println("<script type=\"text/javascript\">");
			out.println("alert('Succesful Sign Up, Redirecting To Login Page....');");
			out.println("</script>");

			RequestDispatcher rs = request
					.getRequestDispatcher("eventOrg_Login.html");
			rs.include(request, response);

		}

	}// end doPost

	private boolean userNameExists(String login) {
		// function to see if user name exists in registered database
		boolean st = false;

		try {
			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/test", "root", "password");
			PreparedStatement ps = con
					.prepareStatement("select * from event_organisers where login=?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			st = rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return st;

	}

	private void addUser(String login, String password, String email) {
		// function to add event organiser to database
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "INSERT INTO event_organisers(login, password, email) values (?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, login);
			statement.setString(2, password);
			statement.setString(3, email);

			statement.executeUpdate();

		} catch (SQLException se) {
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

}
