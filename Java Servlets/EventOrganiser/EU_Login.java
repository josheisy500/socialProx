
import java.io.IOException;
import java.io.PrintWriter;

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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        
        
        //invoke class validate function return true or false
        if(Validate.checkUser(login,pass))
        {
        	//do main menu redirect here
        	RequestDispatcher rs = request.getRequestDispatcher("MainMenu.html");
        	out.print("Welcome " + login);
        	
        	//Create Session here
        	HttpSession session = request.getSession();
        	session.setAttribute("login", login);
        	rs.forward(request, response);
        }
        else{
        	//do forgot password screen here
        	out.println("<script type=\"text/javascript\">");  
			out.println("alert('Sorry User Name or Password Is Invalid...');");  
			out.println("</script>");
			RequestDispatcher rs = request.getRequestDispatcher("eventOrg_Login.html");
            rs.include(request, response);
        }
        
        out.close();
	
	}
}
