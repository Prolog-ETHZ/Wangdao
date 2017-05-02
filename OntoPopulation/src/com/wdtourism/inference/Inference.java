package com.wdtourism.inference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wdtourism.inference.SemiReader.TripleIterator;

public class Inference {
	public static String[] purList = new String[]{"BoostFitness","BuyNeeds","EatNeeds"
			,"ArtAppreciation","CulturalAppreciation","HistoryRemembering","RelationshipEnjoy"
			,"ReligiousAppreciation","SeeSuperStar","StressReleasing"};
	public static String[] bestList = new String[]{"Children","FamilyMembers","Friends"
		,"LovedOnes","OldPeople"	
	};
	public static void main(String[] agrs){
		
		RuleParser rp = new RuleParser("C:/Users/123/Desktop/大四下/毕业论文/rules.txt");
		SemiReader sr = new SemiReader();
		List<SemiAttraction> attrList = new ArrayList<SemiAttraction>(); 
		String fileName=null;
		while((fileName=sr.nextFile())!=null){
			TripleIterator tIter = sr.getIterator(fileName);
			Set<String> actSet = new HashSet<String>();
			Set<String> purSet = new HashSet<String>();
			Set<String> cateSet = new HashSet<String>();
			Set<String> bestSet = new HashSet<String>();
			while(tIter.hasNext()){
				String line = tIter.nextTriple();
				if(line.equals("Unindentified")){
					continue;
				}
				else{
					int indi = contain(line);
					if(indi==1){
						purSet.add(line);
					}
					else if(indi==2){
						bestSet.add(line);
					}else{
						actSet.add(line);
					}
				}
			}
			SemiAttraction attr = new SemiAttraction(fileName.substring(0, fileName.indexOf(".")),actSet, purSet, cateSet, bestSet);
			attrList.add(attr);
		}
		String path = "C:/Users/123/Desktop/大四下/毕业论文/原始数据/semi/";
		PrintWriter pw =  null;
		for(int i=0;i<attrList.size();i++){
			rp.inference(attrList.get(i));
			try {
				pw = new PrintWriter(new File(path+attrList.get(i).getName()+".txt"));
				// for Activity
				Set<String> set = attrList.get(i).getActConcept();
				Iterator<String> iter = set.iterator();
				while(iter.hasNext()){
					pw.println(iter.next()+","+"Act");
				}
				pw.flush();
				// for Purpose
				set = attrList.get(i).getPurConcept();
				iter = set.iterator();
				while(iter.hasNext()){
					pw.println(iter.next()+","+"Pur");
				}
				pw.flush();
				set = attrList.get(i).getBestConcept();
				iter = set.iterator();
				while(iter.hasNext()){
					pw.println(iter.next()+","+"Best");
				}
				pw.flush();
				set = attrList.get(i).getCateConcept();
				iter = set.iterator();
				while(iter.hasNext()){
					pw.println(iter.next()+","+"Cate");
				}
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static int contain(String s){
		for(int i=0;i<purList.length;i++){
			if(purList[i].equals(s)){
				return 1;
			}
		}
		for(int i=0;i<bestList.length;i++){
			if(bestList[i].equals(s)){
				return 2;
			}
		}
			
		return -1;
	}
}
