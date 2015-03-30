
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class CreateProfile
 */

@WebServlet(description = "Servlet to allow mobile client to create profile (stored on MySQL Database)", urlPatterns = { "/CreateProfile" }
public class CreateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/SocialProx";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";
	
	Connection conn;
	Statement stmt;

	public CreateProfile() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Set response content type
		response.setContentType("text/html");

		// get values from client here
		String anonUserName = request.getParameter("anonUsrNm");
		String longitude = request.getParameter("long");
		String latitude = request.getParameter("lat");
		String currentInterest = request.getParameter("crntItst");

		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String sql = "INSERT INTO profile (anonUserName, longitude, latitude, currentInterest) values (?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, anonUserName);
			statement.setString(2, longitude);
			statement.setString(3, latitude);
			statement.setString(4, currentInterest);

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