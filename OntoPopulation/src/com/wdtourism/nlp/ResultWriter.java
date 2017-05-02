package com.wdtourism.nlp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultWriter {
	// writer result
	public static final String path = "C:/Users/123/Desktop/大四下/毕业论文/原始数据/act&pur&acc_extraction/";

	public static void WriteRecord(String name, String concept, Triple t) throws IOException {
		FileWriter fw = new FileWriter(path + name, true);
		fw.write(concept + ":" + t.getSub() + "," + t.getRel() + "," + t.getObj()+"\r\n");
		fw.flush();
		fw.close();
	}

	public static void WriterRecord(String name, List<String> concepts, List<Triple> ts) throws IOException {
		FileWriter fw = new FileWriter(path + name, true);

		if (concepts == null) {

			for (int i = 0; i < ts.size(); i++) {
				Triple t = ts.get(i);

				fw.write("Unindentified" + ":" + t.getSub() + "," + t.getRel() + "," + t.getObj()+"\r\n");

			}
		} else {
			for (int i = 0; i < ts.size(); i++) {
				Triple t = ts.get(i);

				try {
					fw.write(concepts.get(i) + ":" + t.getSub() + "," + t.getRel() + "," + t.getObj()+"\r\n");
				} catch (IndexOutOfBoundsException e) {
					fw.write("Unindentified" + ":" + t.getSub() + "," + t.getRel() + "," + t.getObj()+"\r\n");
				}
			}
		}
		fw.flush();
		fw.close();
	}
}
