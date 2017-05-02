package com.wdtourism.ontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RelationLoader {
	private Scanner sc ;
	HashMap<String,String[]> rTable;
	public RelationLoader(String path){
		rTable = new HashMap<String,String[]>();
		try {
			sc = new Scanner(new File(path));
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String[] arr = line.split(":");
				if(arr.length==2){
					rTable.put(arr[0], arr[1].split(","));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<String> getRelation(String key){
		List<String> rList = new ArrayList<String>();
		String[] arr= rTable.get(key);
		if(arr==null){
			return rList;
		}
		for(int i=0;i<arr.length;i++){
			if(!arr[i].contains("(")){
				rList.add(arr[i]);
			}
		}
		return rList;
	}
}
