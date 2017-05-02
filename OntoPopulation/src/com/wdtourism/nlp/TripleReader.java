package com.wdtourism.nlp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TripleReader {
	private static final String path = "C:/Users/123/Desktop/大四下/毕业论文/原始数据/MoreTriples/";
	private Scanner sc;
	private File[] files;
	private int count = 0;
	private int total;

	public TripleReader() {
		files = new File(path).listFiles();
		total = files.length;
	}

	public String nextFile() {
		if (total > count) {
			return files[count++].getName();
		} else {
			return null;
		}
	}

	public TripleIterator getIterator(String name) {
		return new TripleIterator(name);
	}

	class TripleIterator {
		private String name;
		private Scanner sc;

		public TripleIterator(String name) {
			this.setName(name);
			try {
				sc = new Scanner(new File(path + name));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public boolean hasNext() {
			return sc.hasNext();
		}

		public Triple nextTriple() {
			String line = sc.nextLine();
			
			while(line.contains("/")&&sc.hasNext()){
				line = sc.nextLine();
			}
			if(!Character.isLetter(line.charAt(0))){
				return null;
			}else{
				String rel = line.substring(0,line.indexOf("(")).trim();
				String sub = line.substring(line.indexOf("(")+1,line.indexOf("-")).trim();
				String obj = line.substring(line.indexOf(",")+1,line.lastIndexOf("-")).trim();
				return new Triple(obj,sub,rel);
			}
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}
}
