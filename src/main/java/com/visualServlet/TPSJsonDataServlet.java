package com.visualServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.db.SchemaUtils;
import com.google.gson.Gson;
import com.pojo.TPS;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class TPSJsonDataServlet
 */
@WebServlet("/TPSJsonDataServlet")
public class TPSJsonDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TPSJsonDataServlet() {
        super();
    }
	
	/*public static void main(String[] args) {
		try {
			List<TPS> tpsList = new SchemaUtils().getTPSList();
			Gson gson = new Gson();

			String jsonString = gson.toJson(tpsList);
			System.out.println("jsonString: "+jsonString);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<TPS> tpsList = new SchemaUtils().getTPSList();
			Gson gson = new Gson();

			String jsonString = gson.toJson(tpsList);
			System.out.println("jsonString: "+jsonString);
			
			//System.out.println("uname "+request.getParameter("uname"));
			
			
			/*ClientConfig config = new DefaultClientConfig();
			  Client client = Client.create(config);
			  WebResource service = client.resource(UriBuilder.fromUri("http://10.0.41.231:8080/Rest1.8/api/tps").build());
			  // getting XML data
			  
			  jsonString = service.accept(MediaType.APPLICATION_JSON).get(String.class);
			  System.out.println(jsonString);*/
			  // getting JSON data
			response.setContentType("application/json");
			response.getWriter().write(jsonString);
			
			/*if(request.getParameter("uname").equals("admin1")){
				response.getWriter().write(jsonString);	
			}
			response.getWriter().write("you do not have sufficient privilages");*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		  Client client = Client.create(config);
		  WebResource service = client.resource(UriBuilder.fromUri("http://10.0.41.231:8080/Rest1.8/api/tps").build());
		  // getting XML data
		  System.out.println(service.accept(MediaType.APPLICATION_JSON).get(String.class));
	}

}
