package com.wdtourism.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.wdtourism.networm.AttractionWorm;
import com.wdtourism.networm.RawAttraction;

public class SourceReader {
	private static final String src = "C:/Users/123/Desktop/大四下/毕业论文/原始数据/scene_marker.js";
	private static final String prefix = "http://www.mafengwo.cn/poi/";
	private Scanner sc = null;
	private int count = 0;
	private int prePos = 0;
	public static void main(String[] agrs){
		/*SourceReader sr = new SourceReader(72);
		List<String> pair = null;
		ArrayList<RawAttraction> rawList = new ArrayList<RawAttraction>();
		while(true){
			pair = sr.nextPair();
			if(pair.size()==0){
				break;
			}
			else{
				System.out.println(pair.get(0)+" : "+pair.get(1));
				RawAttraction attr = AttractionWorm.getAttractionComments(pair.get(1), pair.get(0));
				rawList.add(attr);
				RawAttractionWriter.writeToTxT(rawList);
				rawList.clear();
			}
		}*/
		SourceReader sr = new SourceReader(0);
		List<String> pair = null;
		FileWriter fw  = null;
		try {
			fw = new FileWriter("C:/Users/123/Desktop/大四下/毕业论文/原始数据/MatchWih.txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			pair = sr.nextPair();
			if(pair.size()==0){
				break;
			}
			else{
				System.out.println(pair.get(0)+" : "+pair.get(1));
				List<String> list = null;
				try{
					list = AttractionWorm.getAttractionMatchWith(pair.get(1), pair.get(0));
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("couldn't find infomation : "+pair.get(0)+" : "+pair.get(1));
					continue;
				}
				try {
					fw.write(pair.get(0)+":");
					for(int i=0;i<list.size();i++){
						if(i==list.size()-1){
							fw.write(list.get(i)+"\r\n");
						}else{
							fw.write(list.get(i)+",");
						}
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				fw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public SourceReader(int prePos){
		this.prePos = prePos;
		try {
			sc = new Scanner(new File(src));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<String> nextPair(){
		List<String> pair = new ArrayList<String>(2);
		String line="";
		String name="";
		String url ="";
		while(sc.hasNextLine()){
			line = sc.nextLine();
			
			if(line.contains("name")){
				name = line.substring(line.indexOf("=")+1, line.length());
			}
			if(line.contains("web2")){
				url = line.substring(line.indexOf("=")+1, line.length());
			    url = url.substring(1,url.length()-2);
				if(!url.equals("0.html")){
					count++;
					if(count>prePos){
						pair.add(name.substring(1, name.length()-2));
						pair.add(prefix+url);
						break;
					}
				}
			}
		}
	
		
		return pair;
	}
	
}
