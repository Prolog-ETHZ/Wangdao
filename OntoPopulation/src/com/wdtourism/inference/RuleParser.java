package com.wdtourism.inference;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RuleParser {
	private  List<Rule> rList;
    public RuleParser(String path) {
		rList = new ArrayList<Rule>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (sc.hasNext()) {
		
			String line = sc.nextLine();
			
			String head = line.substring(0, line.indexOf("->"));
			String tail = line.substring(line.indexOf("->") + 2, line.length());
			Rule r = new Rule(head.split(";"), tail.split(";"));
			rList.add(r);
		}
		sc.close();
	}
    // Rule Format X:xxx;Y:yyy->Z:zzz
    // A is Activity P is Purpose B:best C:Category
    public void inference(SemiAttraction semi){
    	boolean change = false;
    	do{
    		change = false;
    		for(int i=0;i<rList.size();i++){
    			Rule r = rList.get(i);
    			if(contain(r.getHead(),semi)){
    				change = true;
    				for(int j=0;j<r.getTail().length;j++){
    					String newCon = r.getTail()[j];
    					int indi = Inference.contain(newCon);
    					if(indi==1){
    						if(semi.getPurConcept().contains(newCon)){
    							change = false;
    						}else{
    							semi.addPurConcept(newCon);
    						}
    					}
    					else if(indi==2){
    						if(semi.getBestConcept().contains(newCon)){
    							change = false;
    						}else{
    							semi.addBestConcept(newCon);
    						}
    					}else{
    						if(semi.getActConcept().contains(newCon)){
    							change = false;
    						}else{
    							semi.addActConcept(newCon);
    						}
    						
    					}
    				}
    			}
    		}
    	}while(change);
    }
	private boolean contain(String[] head,SemiAttraction semi) {
		// TODO Auto-generated method stub
		for(int i=0;i<head.length;i++){
			if(!semi.getActConcept().contains(head[i])&&!semi.getPurConcept().contains(head[i])
					&&!semi.getCateConcept().contains(head[i])&&!semi.getBestConcept().contains(head[i])){
				return false;
			}
		}
		return true;
	}
	
}
