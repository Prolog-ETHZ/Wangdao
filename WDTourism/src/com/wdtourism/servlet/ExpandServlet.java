package com.wdtourism.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wdtourism.bean.Attraction;
import com.wdtourism.bean.Graph;
import com.wdtourism.bean.Link;
import com.wdtourism.bean.Message;
import com.wdtourism.bean.Node;

public class ExpandServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ExpandServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setContentType("application/json");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Graph g = new Graph();
		HashMap<String,Node> nodemap = new HashMap<String,Node>();
		  HashMap<String,Link> linkmap = new HashMap<String,Link>();
		   
		String relationName="";
		
		ArrayList<String> retStr = new ArrayList<String>();
		//PrintWriter out = response.getWriter();
	    String id = request.getParameter("id");
	    int action = Integer.parseInt(request.getParameter("action"));
	    if(action == 1){
	    	relationName = "临近景点";
	    }
	    else if(action == 2){
	    	relationName="路线匹配";
	    }
	    else if(action == 3){
	    	relationName = "相似景点";
	    }
	   /* Attraction src = new Attraction();
	    src.setName(id);
	    
    	src.setType("景点");
    	nodemap.put(id+"!", src);
	    
	   */
	    Message m = new Message(action,id);
	    Socket link = null;
		try{
			link = new Socket("127.0.0.1",1234);
			ObjectOutputStream out = new ObjectOutputStream(link.getOutputStream());  
		    ObjectInputStream in = new ObjectInputStream(link.getInputStream());  
			out.writeObject(m);
			retStr=(ArrayList<String>)in.readObject();
		    for(int i=0;i<retStr.size();i++){
		    	System.out.println(retStr.get(i));
		    	if(retStr.get(i).equals(id)){
		    		continue;
		    	}
		    	Attraction attr = new Attraction();
		    	attr.setName(retStr.get(i));
		    	attr.setType("景点");
		    	nodemap.put(retStr.get(i), attr);
		    	Link l1 = new Link();
				l1.setFrom(id);
				l1.setTo(retStr.get(i));
				l1.setName(relationName);
				linkmap.put(id+"&"+retStr.get(i), l1);
		    }
		    g.setLinks(linkmap);
		    g.setNodes(nodemap);
			// fake example
		/*	Attraction taipei101 = new Attraction();
			taipei101.setName("Taipei 101");
			taipei101.setType("Attraction");
			
			Attraction mountain = new Attraction();
			mountain.setName("陽明山國家公園");
			mountain.setType("Attraction");
			
			Attraction muesum = new Attraction();
			muesum.setName("國立故宮博物院");
			muesum.setType("Attraction");
			
			Attraction night = new Attraction();
			night.setName("士林觀光夜市(士林夜市)");
			night.setType("Attraction");
			
			HashMap<String,Node> nodemap = new HashMap<String,Node>();
			nodemap.put("taipei101",taipei101);
			nodemap.put("mountain",mountain);
			nodemap.put("muesum",muesum);
			nodemap.put("night",night);
		    g.setNodes(nodemap);
		    
		    
		    
		    Link l1 = new Link();
		    l1.setFrom("mountain");
		    l1.setTo("muesum");
		    l1.setName("路线匹配");
		    
		    Link l2 = new Link();
		    l2.setFrom("muesum");
		    l2.setTo("taipei101");
		    l2.setName("路线匹配");
		    
		    Link l3 = new Link();
		    l3.setFrom("taipei101");
		    l3.setTo("night");
		    l3.setName("临近");
		    
		    HashMap<String,Link> linkmap = new HashMap<String,Link>();
		    g.setLinks(linkmap);
		    g.addLink("Link1", l1);
		    g.addLink("Link2", l2);
		    g.addLink("Link3", l3);*/
			
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		link.close();
		//response.getWriter().write(JSONArray.fromObject(retStr).toString());
		response.getWriter().write(JSONObject.fromObject(g).toString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
