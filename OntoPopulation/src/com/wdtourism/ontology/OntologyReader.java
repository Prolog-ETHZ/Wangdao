package com.wdtourism.ontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class OntologyReader {
	private static final String path = "C:/Users/123/Desktop/大四下/毕业论文/原始数据/semi/";
	
	private File[] files;
	private int count = 0;
	private int total;

	public OntologyReader() {
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

		public String[] nextTriple() {
			String line = sc.nextLine();
			return line.split(",");
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
