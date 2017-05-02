package com.wdtourism.nlp;


import java.util.List;


import com.wdtourism.utils.JdbcUtils;

import ruc.irm.similarity.word.CharBasedSimilarity;
import ruc.irm.similarity.word.cilin.Cilin;
import ruc.irm.similarity.word.hownet2.concept.LiuConceptParser;
import ruc.irm.similarity.word.hownet2.concept.XiaConceptParser;

public class InfoExactor {
	private List<String[]> table;
	private LiuConceptParser lp;
	private Cilin cl;
	private  XiaConceptParser xcp;
	private CharBasedSimilarity cbs;
	//private HashMap<String,Conception> map;
// core function 
	public String match(Triple t){
		for(int i=0;i<table.size();i++){
			String[] pair = table.get(i);
			String obj = t.getObj();
			String sub = t.getSub();
			
			// 0 is CH 1 is EN
			if(obj.equals(pair[0])||sub.equals(pair[0])){
				return pair[1];
			}
			else if(similar(obj,pair[0])){
				if(obj.length()==1){
					return null;
				}
				return pair[1];
			}
			else if(similar(sub,pair[0])){
				if(sub.length()==1){
					return null;
				}
				return pair[1];
			}
		    /*if(t.getObj().equals(pair[0])||t.getSub().equals(pair[0])){
				return pair[1];
			}*/
			
		}
		return null;
	}
	 private boolean similar(String str1,String str2){
		  double ev1 = cl.getSimilarity(str1, str2);
		  if(ev1<0.1){
			  return false;
		  }
		  double ev2 =lp.getSimilarity(str1,str2);
		  if(ev2<0.1){
			  return false;
		  }
		  double ev3 =cbs.getSimilarity(str1,str2);
		  if(ev3<0.1){
			  return false;
		  }
		  double ev4 = xcp.getSimilarity(str1, str2);
		  if(ev4<0.1){
			  return false;
		  }
		  double sum = 0.218*ev1+0.082*ev2+0.383*ev3;
		  if(sum>0.46){
			 return true;
		   }else{
			   return false;
		   }
		  
	  }
	public InfoExactor(){
		table = new JdbcUtils().getMatchTable();
		 lp = LiuConceptParser.getInstance();
		 cl = Cilin.getInstance();
		 xcp = XiaConceptParser.getInstance();
		 cbs = new CharBasedSimilarity();
		/*map = new HashMap<String,Conception>();
		for(int i=0;i<table.size();i++){
			// 0 is HanZi 1 is EN
			String[] pair = table.get(i);
			if(map.containsKey(pair[1])){
				Conception c  = map.get(pair[1]);
			}else{
				
			}
		}*/
	}
}
