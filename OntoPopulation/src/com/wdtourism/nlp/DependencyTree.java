package com.wdtourism.nlp;


import java.util.Collection;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.simple.*;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.international.pennchinese.ChineseGrammaticalStructure;

/**
 * LexicalizedParser.loadModel(modelpath); 加载模型比较消耗时间，可以把这一步以加载到服务中启动
 * @author 胡慧超
 *
 */
public class DependencyTree {
	public static final String modelpath="edu/stanford/nlp/models/lexparser/xinhuaFactored.ser.gz";
	private LexicalizedParser lp;
	private DependencyTree(){
		String[] options = { "-maxLength", "140", "-MAX_ITEMS","500000"};  
		lp = LexicalizedParser.loadModel(modelpath,options);
	}
	private static class ParserHolder{
		public static final DependencyTree parser = new DependencyTree();
	}
	public static  LexicalizedParser getParser(){
		return ParserHolder.parser.lp;
	}
	public static void main(String[] args) {
		String modelpath="edu/stanford/nlp/models/lexparser/xinhuaFactored.ser.gz";
		String str="台北 101 大楼 是 目前 全球 第二 高 大楼 ， 仅次于 迪拜 的 哈法利塔 。 101 大楼 集 办公 大楼 、 观景台 和 购物 中心 于 一体 ， 是 台北市 的 地标性 建筑 。 89 楼 为 室内 观景层 ， 91 楼 为 室外 观景台 ， 这里 有 全方位 绝佳 的 观景 视野 、 纪念 证书 的 摄影 服务 、 语音 导览 柜台 、 冰淇淋 商店 、 纪念品 商店 、 另 有 世界 最 高 的 信箱 ， 可 在 观景台 将 祝福 寄给 国内外 的 好友 。 南侧 还有 91 楼 售票处 与 出入口 （ 北侧 为 出口 ） 。";
	    LexicalizedParser lp = LexicalizedParser.loadModel(modelpath); 
	    
	    Tree t = lp.parse(str);  
//	    TokenizerFactory<CoreLabel> tokenizerFactory =PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
//	    List<CoreLabel> rawWords2 =tokenizerFactory.getTokenizer(new StringReader(str)).tokenize();
//	    Tree t1 = lp.apply(rawWords2);
//	    ChineseTreebankLanguagePack tlp = new ChineseTreebankLanguagePack();
//	    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
	    ChineseGrammaticalStructure gs = new ChineseGrammaticalStructure(t);
//	    List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
	    Collection<TypedDependency> tdl = gs.typedDependenciesCollapsed();
	    System.out.println(tdl.toString());
	    String s="";
	    for(int i = 0;i < tdl.size();i ++)
        {
            TypedDependency td = (TypedDependency)tdl.toArray()[i];
            String age = td.dep().toString();
            s+=age+"/";
        }
	    System.out.println(s);
	   
      
	}
}