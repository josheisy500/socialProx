package com.joshua.databaseAccess;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class createEvent to handle the creation of events made by event organisers
 * to assign the appropriate events to the right mysql database 
 */
@WebServlet("/createEvent")
@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
public class createEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/TEST";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";

	Connection conn;
	Statement stmt;
	
	String createdBy; //variable to hold session log in 

	public createEvent() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        
        HttpSession session=request.getSession(false);  
        if(session!=null){  
            String login=(String)session.getAttribute("login");
            
            out.println("hello," +login+ "Welcome to event creation");
            request.getRequestDispatcher("createEvent.html").include(request, response);  

        }
        
        else {
        	out.println("<script type=\"text/javascript\">");  
			out.println("alert('Woah! Please Login First');");  
			out.println("</script>");
        	request.getRequestDispatcher("eventOrg_Login.html").include(request, response);
        }
        out.close();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);  
        if(session!=null){  
            String login=(String)session.getAttribute("login");
            createdBy = login;
        }
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String eventName = request.getParameter("eventName");
		String date = request.getParameter("date");
		String longitude = request.getParameter("long");
		String latitude = request.getParameter("lat");
		String eventType = request.getParameter("eventType");

		InputStream inputStream = null; // input stream of the upload file
		
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}

		//function to add event to database
		//System.out.println(date);
		addEvent(eventName, date, longitude, latitude, eventType, inputStream, createdBy);
		
		//do forgot password screen here
    	out.println("<script type=\"text/javascript\">");  
		out.println("alert('Event Created, Redirecting To Main Menu');");  
		out.println("</script>");
		RequestDispatcher rs = request.getRequestDispatcher("MainMenu.html");
		rs.include(request, response);


	}

	private void addEvent(String eventName, String date, String longitude,
			String latitude, String eventType, InputStream inputStream, String createdBy) {

		String message = null;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//insert event into sports_event database
			if (eventType.equals("sports")) {
				String sql = "INSERT INTO sports_events (name, longitude, latitude, date, event_photo, createdBy) values (?,?,?,?,?,?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, eventName);
				statement.setString(2, longitude);
				statement.setString(3, latitude);
				statement.setString(4, date);
				

				if (inputStream != null) {
					// fetches input stream of the upload file for the blob // column
					statement.setBlob(5, inputStream);
				}
				statement.setString(6, createdBy);

				// sends the statement to the database server
				int row = statement.executeUpdate();
				if (row > 0) {
					message = "File uploaded and saved into database";
				}
			}//end if sport event
			
			//insert event into food_drink database
			else if(eventType.equals("food_drink")){
				String sql = "INSERT INTO food_drink_events (name, longitude, latitude, date, event_photo, createdBy) values (?,?,?,?,?,?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, eventName);
				statement.setString(2, longitude);
				statement.setString(3, latitude);
				statement.setString(4, date);
				

				if (inputStream != null) {
					// fetches input stream of the upload file for the blob
					// column
					statement.setBlob(5, inputStream);
				}
				statement.setString(6, createdBy);

				// sends the statement to the database server
				int row = statement.executeUpdate();
				if (row > 0) {
					message = "File uploaded and saved into database";
				}

			}
			
			//insert event into music_events database
			else{
				String sql = "INSERT INTO music_events (name, longitude, latitude, date, event_photo, createdBy) values (?,?,?,?,?,?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, eventName);
				statement.setString(2, longitude);
				statement.setString(3, latitude);
				statement.setString(4, date);
				

				if (inputStream != null) {
					// fetches input stream of the upload file for the blob
					// column
					statement.setBlob(5, inputStream);
				}
				
				statement.setString(6, createdBy);

				// sends the statement to the database server
				int row = statement.executeUpdate();
				if (row > 0) {
					message = "File uploaded and saved into database";
				}
				
			}

			// String sql = "INSERT INTO profile (  "
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
