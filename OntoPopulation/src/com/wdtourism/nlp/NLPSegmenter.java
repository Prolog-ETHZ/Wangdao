package com.wdtourism.nlp;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

public class NLPSegmenter {
	private static final String basedir = "C:/Users/123/workspace/OntoPopulation/lib/stanford-segmenter-2015-12-09/data";
	private CRFClassifier<CoreLabel> segmenter;
	private NLPSegmenter() {
		try {
			System.setOut(new PrintStream(System.out, true, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Properties props = new Properties();
		props.setProperty("sighanCorporaDict", basedir);
		// props.setProperty("NormalizationTable", "data/norm.simp.utf8");
		// props.setProperty("normTableEncoding", "UTF-8");
		// below is needed because CTBSegDocumentIteratorFactory accesses it

		props.setProperty("serDictionary", basedir + "/dict-chris6.ser.gz");
		/*
		 * if (args.length > 0) { props.setProperty("testFile", args[0]); }
		 */
	//	props.setProperty("testFile", "C:/Users/123/Desktop/大四下/毕业论文/原始数据/Corpus/Taipei 101.txt");
		props.setProperty("inputEncoding", "UTF-8");
		props.setProperty("sighanPostProcessing", "true");

		segmenter = new CRFClassifier<>(props);
		segmenter.loadClassifierNoExceptions(basedir + "/ctb.gz", props);
	}
	private static class SegmenterHolder{
		public static final NLPSegmenter nlps = new NLPSegmenter();
	}
	public static CRFClassifier<CoreLabel> getSegmenter(){
		return SegmenterHolder.nlps.segmenter;
	}
}
