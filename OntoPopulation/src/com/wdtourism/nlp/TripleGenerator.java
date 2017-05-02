package com.wdtourism.nlp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseGrammaticalStructure;

public class TripleGenerator {
	public static void main(String[] args) throws FileNotFoundException {
		String triplePath  = "C:/Users/123/Desktop/大四下/毕业论文/原始数据/MoreTriples/";
		File[] existFiles = new File(triplePath).listFiles();
		String path = "C:/Users/123/Desktop/大四下/毕业论文/原始数据/MoreCorpus";
		File dir = new File(path);
		File[] files = dir.listFiles();
		CRFClassifier<CoreLabel> seg = NLPSegmenter.getSegmenter();
		LexicalizedParser parser = DependencyTree.getParser();
		Scanner sc = null;
		PrintWriter pw = null;
		String attrName = null;
		ChineseGrammaticalStructure gs = null;
		Collection<TypedDependency> tdl = null;
		boolean exist = false;
		for (File f : files) {
			for(int i=0;i<existFiles.length;i++){
				String f1 = existFiles[i].getName();
				String f2 = f.getName();
				
				if(f2.equals(f1)){
					exist = true;
					break;
				}
			}
			if(exist){
				exist = false;
				continue;
				
			}
			attrName = f.getName().substring(0, f.getName().indexOf("."));
			sc = new Scanner(new File(f.getAbsolutePath()));
			pw = new PrintWriter(
					new File(triplePath + attrName + ".txt"));
			String sample = null;
			List<String> segmented = null; 
			while (sc.hasNextLine()) {
				sample = sc.nextLine();
				segmented = seg.segmentString(sample);
				System.out.println(segmented);
				String splited = "";
				for (int i = 0; i < segmented.size(); i++) {
					splited += segmented.get(i) + " ";
				}
				System.out.println(splited);
				String[] splitArray = splited.split("。|；|！|，");
				
				for (int num = 0; num < splitArray.length; num++) {
					String tar = splitArray[num];
				    tar = tar.replaceAll("<[//s//S]+>", "");
					Tree t = parser.parse(tar);
					gs = new ChineseGrammaticalStructure(t);
					tdl = gs.typedDependenciesCollapsed();
					System.out.println(tdl.toString());
					String s = "";
					for (int i = 0; i < tdl.size(); i++) {
						TypedDependency td = (TypedDependency) tdl.toArray()[i];
						String age = td.dep().toString();
						s += age + "/";
					}
					System.out.println(s);
					
					pw.println(s);
					Iterator<TypedDependency> iter = tdl.iterator();
					while (iter.hasNext()) {
						TypedDependency td = iter.next();
						pw.println(td.toString());
					}
				}
			

			}
			pw.flush();
			pw.close();
			sc.close();
			System.gc();

		}
	}
}
