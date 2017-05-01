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

import net.sf.json.JSONObject;

import com.wdtourism.bean.Attraction;
import com.wdtourism.bean.Graph;
import com.wdtourism.bean.Link;
import com.wdtourism.bean.Message;
import com.wdtourism.bean.Node;
import com.wdtourism.bean.Route;

public class AdvancedSearch extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdvancedSearch() {
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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setContentType("text/html");
		response.setContentType("application/json");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Graph g = new Graph();
		ArrayList<String> retStr = null;
		ArrayList<Route> rList = null;
		String sentence = (String) request.getParameter("searchWord");
		int mode = Integer.parseInt(request.getParameter("mode"));
		Message m = null;
		if (mode == 1) {
			m = new Message(Message.GEO, sentence);
		} else {
			m = new Message(Message.SENTENCE, sentence);
		}
		Socket link = null;
		HashMap<String, Node> nodemap = new HashMap<String, Node>();
		HashMap<String, Link> linkmap = new HashMap<String, Link>();

		try {
			link = new Socket("127.0.0.1", 1234);
			ObjectOutputStream out = new ObjectOutputStream(link
					.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(link.getInputStream());

			out.writeObject(m);
			if (mode == 1) {
				rList = (ArrayList<Route>)in.readObject();
				for(int i=0;i<rList.size();i++){
					Route r = rList.get(i);
					for(int j=0;j<r.getList().size();j++){
						Attraction attr = new Attraction();
						attr.setName(r.getList().get(j));
						attr.setType("景点");
						nodemap.put(r.getList().get(j), attr);
						if(j<r.getList().size()-1){
							Link l = new Link();
							l.setFrom(r.getList().get(j));
							l.setTo(r.getList().get(j+1));
							l.setName("一下站");
							linkmap.put(r.getList().get(j)+"&"+r.getList().get(j+1), l);
						}
					}
				}
				g.setNodes(nodemap);
				g.setLinks(linkmap);
				
			} else {
				retStr = (ArrayList<String>) in.readObject();
				for (int i = 0; i < retStr.size(); i++) {
					Attraction attr = new Attraction();
					attr.setName(retStr.get(i));
					attr.setType("景点");
					nodemap.put(retStr.get(i), attr);

				}
				g.setNodes(nodemap);

				
				g.setLinks(linkmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		link.close();

		response.getWriter().write(JSONObject.fromObject(g).toString());
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
