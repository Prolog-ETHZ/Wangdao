package com.wdtourism.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.wdtourism.networm.RawAttraction;

public class RawAttractionWriter {
	public static void writeToTxT(List<RawAttraction> attrs){
		PrintWriter pw =null;
		for(int i=0;i<attrs.size();i++){
			try {
				pw = new PrintWriter(new File("C:/Users/123/Desktop/大四下/毕业论文/原始数据/MoreCorpus/"+attrs.get(i).getName()+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.println(attrs.get(i).getIntro());
			pw.flush();
			pw.close();
		}
		
	}
}
