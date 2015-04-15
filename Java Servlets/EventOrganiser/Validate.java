
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validate {

	public static boolean checkUser(String login, String pass) {
		boolean st = false;
		try {

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/test", "root", "password");

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

	public static boolean checkUserName(String login) {
		boolean st = false;

		try {
			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "password");
			PreparedStatement ps = con.prepareStatement("select * from event_organisers where login=?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			st = rs.next();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return st;

	}
}
