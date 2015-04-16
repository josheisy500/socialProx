import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EU_Login
 */
@WebServlet("/eventOrg_Login")
public class EU_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EU_Login() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String login = request.getParameter("login");
		String pass = request.getParameter("pass");

		if (checkUserCredentials(login, pass)) {
			// do main menu redirect here
			RequestDispatcher rs = request
					.getRequestDispatcher("MainMenu.html");
			out.print("Welcome " + login);

			// Create Session here
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			rs.forward(request, response);
		} else {
			// do forgot password screen here
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Sorry User Name or Password Is Invalid...');");
			out.println("</script>");
			RequestDispatcher rs = request
					.getRequestDispatcher("eventOrg_Login.html");
			rs.include(request, response);
		}

		out.close();

	}

	public static boolean checkUserCredentials(String login, String pass) {
		boolean st = false;
		try {

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/TEST", "root", "password");

			PreparedStatement ps = con
					.prepareStatement("select * from event_organisers where login=? and password=?");
			ps.setString(1, login);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			st = rs.next(); // finds a match st = true

		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}
}
