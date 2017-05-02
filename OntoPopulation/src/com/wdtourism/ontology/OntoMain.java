package com.wdtourism.ontology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wdtourism.inference.SemiAttraction;
import com.wdtourism.ontology.OntologyReader.TripleIterator;
import com.wdtourism.utils.JdbcUtils;

public class OntoMain {
	public static void main(String[] args) {
		String fileName = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:/Users/123/Desktop/大四下/毕业论文/Test.txt", true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// List<SemiAttraction> attrList = new ArrayList<SemiAttraction>();
		OntologyReader or = new OntologyReader();
		while ((fileName = or.nextFile()) != null) {
			System.out.println(fileName+":"+JdbcUtils.FrontToEnd(fileName.substring(0, fileName.indexOf("."))));
			TripleIterator tIter = or.getIterator(fileName);
			Set<String> actSet = new HashSet<String>();
			Set<String> purSet = new HashSet<String>();
			Set<String> cateSet = new HashSet<String>();
			Set<String> bestSet = new HashSet<String>();
			while (tIter.hasNext()) {
				String[] pair = tIter.nextTriple();
				if ("Act".equals(pair[1])) {
					actSet.add(pair[0]);
				} else if ("Pur".equals(pair[1])) {
					purSet.add(pair[0]);
				} else if ("Best".equals(pair[1])) {
					bestSet.add(pair[0]);
				} else if ("Cate".equals(pair[1])) {
					cateSet.add(pair[0]);
				} else {

				}
			}
			OntologyWriter.startInstance(fileName.substring(0, fileName.indexOf(".")));
			// Undone
			OntologyWriter.writeCate(cateSet);
			// Undone
			OntologyWriter.writeAct(actSet);
			OntologyWriter.writePur(purSet);
			OntologyWriter.writeBest(bestSet);
			OntologyWriter.writeName(fileName.substring(0, fileName.indexOf(".")));
			OntologyWriter.writeStar(4);
			OntologyWriter.writeX(1.1);
			OntologyWriter.writeY(1.2);
			OntologyWriter.writeTime(2);
			RelationLoader nearR = new RelationLoader("C:/Users/123/Desktop/大四下/毕业论文/原始数据/nearBy.txt");
			RelationLoader matchR = new RelationLoader("C:/Users/123/Desktop/大四下/毕业论文/原始数据/MatchWih.txt");
			OntologyWriter.writeNearBy(nearR.getRelation(fileName.substring(0, fileName.indexOf("."))));
			OntologyWriter.writeMatchWith(matchR.getRelation(fileName.substring(0, fileName.indexOf("."))));
			// Undone 
			
			OntologyWriter.writeSimilar(new ArrayList<String>());
			// Undone 
			OntologyWriter.endInstance();
			String onto = OntologyWriter.getOntology();
			try {

				fw.write(onto + "\r\n");
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
}
