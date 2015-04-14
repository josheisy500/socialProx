package com.joshua.databaseAccess;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class eventOrg_Logout
 */
@WebServlet("/eventOrg_Logout")
public class eventOrg_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public eventOrg_Logout() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        request.getRequestDispatcher("eventOrg_Login.html").include(request, response);  
          
        HttpSession session=request.getSession();
        String login=(String)session.getAttribute("login");

        session.invalidate(); 
        
        out.print("Goodbye! "+ login + "You are successfully logged out!");  
        
        out.close(); 
        

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
