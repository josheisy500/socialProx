package com.joshua.databaseAccess;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EU_Login
 */
@WebServlet("/eventOrg_Login")
public class EU_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public EU_Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        
        
        //invoke validate function return true or false
        if(Validate.checkUser(login,pass))
        {
        	//do main menu redirect here
        	RequestDispatcher rs = request.getRequestDispatcher("MainMenu.html");
        	rs.forward(request, response);
        }
        else{
        	//do forgot password screen here
        	out.println("Username or Password invalid");
        	RequestDispatcher rs = request.getRequestDispatcher("eventOrg_Login.html");
            rs.include(request, response);
        }
	}

}
