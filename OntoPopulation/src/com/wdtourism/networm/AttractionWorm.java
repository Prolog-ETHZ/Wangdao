package com.wdtourism.networm;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wdtourism.utils.RawAttractionWriter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AttractionWorm {
	public static RawAttraction getAttraction(String url, String name) {
		RawAttraction ra = new RawAttraction();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element el = doc.getElementsByClass("intro").get(0).getElementsByTag("span").get(0);

		ra.setIntro(el.html());
		ra.setName(name);
		return ra;
	}

	public static void main(String[] agrs) {
		ArrayList<RawAttraction> list = new ArrayList<RawAttraction>();
		try {
			AttractionWorm.getAttractionMatchWith("http://www.mafengwo.cn/poi/5429682.html", "Test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// RawAttractionWriter.writeToTxT(list);
	}

	public static RawAttraction getAttractionComments(String url, String name) {
		RawAttraction ra = new RawAttraction();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(10000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element e = doc.getElementsByClass("rev-lists").get(0).getElementsByClass("rev-txt").get(0);
		ra.setIntro(e.html());
		ra.setName(name);
		return ra;
	}

	public static List<String> getAttractionNearBy(String url, String name) throws IOException {
		List<String> list = new ArrayList<String>();
		Document doc = null;

		doc = Jsoup.connect(url).get();
		// System.out.println(doc);
		Elements es = doc.getElementsByClass("row-scenic").get(0).getElementsByClass("clearfix").get(0).children();
		for (int i = 0; i < es.size(); i++) {
			list.add(es.get(i).getElementsByTag("h3").html());
		}

		return list;
	}

	public static List<String> getAttractionMatchWith(String url, String name) throws IOException {
		List<String> list = new ArrayList<String>();
		Document doc = null;

		doc = Jsoup.connect(url).timeout(10000).get();
		String html = doc.html();
		Pattern p = Pattern.compile("var related_spots = \\[[\\s\\S]*?\\],");
//		System.out.println(html);
		Matcher m = p.matcher(html);
		String spots = null;
		while(m.find()){
			spots = m.group();
		}
		spots = spots.substring(spots.indexOf("=")+1, spots.length()-1).trim();
		JSONArray json = JSONArray.fromObject(spots);
		for(int i=0;i<json.size();i++){
			JSONObject obj = JSONObject.fromObject(json.get(i));
			if(obj.get("type").toString().equals("biyou")){
				list.add(obj.getString("name"));
			}
		}
		return list;
	}
	

}
