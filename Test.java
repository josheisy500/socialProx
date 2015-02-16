import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONObject;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;


public class Test extends HttpServlet {

   
    public void init() throws ServletException
    {
	
    }
    
    public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
           throws ServletException, IOException
    {
		JSONObject mainObj  = retrieveData();
	
		response.setContentType("application/json");
		String output = mainObj.toString();
		PrintWriter writer = response.getWriter();
		writer.write(output);
		writer.close();

     }

    public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
           throws ServletException, IOException
    {
		String jsonData = request.getParameter("json");
		response.setContentType("application/json");	
		PrintWriter out = response.getWriter();
		out.println(jsonData);
		out.close();	
    }

    //enter some dummy data to retrieve on movbile device 
    private JSONObject retrieveData()
    {
    	JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
	
       	json.put("Country", "Ireland");
		json.put("Capital", "Dublin");
		ja.put(json);
		json = new JSONObject();
	
		json.put("Country", "Spain");
		json.put("Capital", "Madrid");
		ja.put(json);
		json = new JSONObject();
	
		json.put("Country","France");
		json.put("Capital", "Paris");	
		ja.put(json);
		json = new JSONObject();

	
		JSONObject mainObj = new JSONObject();
		mainObj.put("places", ja);
		
		return mainObj;
    }
     
    public void destroy()
    {
	//nothing
    }

    
}
