package com.wdtourism.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wdtourism.bean.Attraction;
import com.wdtourism.bean.Graph;
import com.wdtourism.bean.Link;
import com.wdtourism.bean.Node;

public class ExampleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ExampleServlet() {
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

		response.setContentType("text/html");
		response.setContentType("application/json");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Graph g = new Graph();
		// first example case
		Attraction taipei101 = new Attraction();
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
	    g.addLink("Link3", l3);
	    
	    
		// second example case
	/*	Attraction sisi = new Attraction();
		sisi.setName("四四南村");
		sisi.setType("Attraction");
		
		Attraction sima = new Attraction();
		sima.setName("司马司库越岭");
		sima.setType("Attraction");
		
		Attraction nanzhuang = new Attraction();
		nanzhuang.setName("南庄");
		nanzhuang.setType("Attraction");
		
		Attraction xlake = new Attraction();
		xlake.setName("向天湖");
		xlake.setType("Attraction");
		
		Attraction minsu = new Attraction();
		minsu.setName("台湾民俗村");
		minsu.setType("Attraction");
		
		Attraction ALi = new Attraction();
		ALi.setName("阿里山");
		ALi.setType("Attraction");
		
		Attraction dana = new Attraction();
	    dana.setName("达娜伊谷");
		dana.setType("Attraction");
		
		Attraction meinong = new Attraction();
	    meinong.setName("美浓");
		meinong.setType("Attraction");
		
		Attraction neiwan = new Attraction();
	    neiwan.setName("内湾车站");
		neiwan.setType("Attraction");
		
		Attraction shifen = new Attraction();
	    shifen.setName("十分车站");
		shifen.setType("Attraction");
		
		Attraction riyuetan = new Attraction();
	    riyuetan.setName("日月潭");
		riyuetan.setType("Attraction");
		
		Attraction fenqihu = new Attraction();
	    fenqihu.setName("奋起湖");
		fenqihu.setType("Attraction");
		
		Attraction kending = new Attraction();
	    kending.setName("垦丁");
		kending.setType("Attraction");
		
		HashMap<String,Node> nodes = new HashMap<String,Node>();
		nodes.put("sisi", sisi);
		nodes.put("sima", sima);
		nodes.put("nanzhuang", nanzhuang);
		nodes.put("xlake", xlake);
		nodes.put("minsu", minsu);
		nodes.put("ALi", ALi);
		nodes.put("dana", dana);
		nodes.put("meinong", meinong);
		nodes.put("neiwan", neiwan);
		nodes.put("shifen", shifen);
		nodes.put("riyuetan", riyuetan);
		nodes.put("fenqihu", fenqihu);
		nodes.put("kending", kending);
		g.setNodes(nodes);
		
		Link l1 = new Link();
		l1.setFrom("sisi");
		l1.setTo("sima");
		l1.setName("主干路线");
		
		Link l2 = new Link();
		l2.setFrom("sima");
		l2.setTo("nanzhuang");
		l2.setName("主干路线");
		
		Link l3 = new Link();
		l3.setFrom("nanzhuang");
		l3.setTo("xlake");
		l3.setName("主干路线");
		
		Link l4 = new Link();
		l4.setFrom("xlake");
		l4.setTo("minsu");
		l4.setName("主干路线");
		
		Link l5 = new Link();
		l5.setFrom("minsu");
		l5.setTo("ALi");
		l5.setName("主干路线");
		
		Link l6 = new Link();
		l6.setFrom("ALi");
		l6.setTo("dana");
		l6.setName("主干路线");
		
		Link l7 = new Link();
		l7.setFrom("dana");
		l7.setTo("meinong");
		l7.setName("主干路线");
		
		Link l8 = new Link();
		l8.setFrom("sima");
		l8.setTo("neiwan");
		l8.setName("路线匹配");
		
		Link l9 = new Link();
		l9.setFrom("neiwan");
		l9.setTo("shifen");
		l9.setName("相似景点");
		
		Link l10 = new Link();
		l10.setFrom("minsu");
		l10.setTo("riyuetan");
		l10.setName("路线匹配");
		
		Link l11 = new Link();
		l11.setFrom("ALi");
		l11.setTo("fenqihu");
		l11.setName("相邻");
		
		Link l12 = new Link();
		l12.setFrom("meinong");
		l12.setTo("kending");
		l12.setName("路线匹配");
		
		Map<String,Link> links = new HashMap<String,Link>();
		links.put("L1", l1);
		links.put("L2", l2);
		links.put("L3", l3);
		links.put("L4", l4);
		links.put("L5", l5);
		links.put("L6", l6);
		links.put("L7", l7);
		links.put("L8", l8);
		links.put("L9", l9);
		links.put("L10", l10);
		links.put("L11", l11);
		links.put("L12", l12);
		g.setLinks(links);
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		// return a string of json.
	    response.getWriter().write(JSONObject.fromObject(g).toString());
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
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
