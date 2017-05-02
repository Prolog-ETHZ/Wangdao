package com.wdtourism.ontology;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wdtourism.utils.JdbcUtils;

public class OntologyWriter {
	/*
	 * <owl:NamedIndividual rdf:about=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#BaiShaiWan">
	 * <rdf:type rdf:resource=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#GeoScenery"/>
	 * <rdf:type rdf:resource=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#SeaScenery"/>
	 * <rdf:type rdf:resource=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#WaterfrontDistrict"
	 * /> <hasActivity rdf:resource=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#BoatExcursion"/
	 * > <hasActivity rdf:resource=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#Surfing"/>
	 * <hasActivity rdf:resource=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#Swimming"/>
	 * <hasActivity rdf:resource=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#WaterSkiing"/>
	 * <locatedIn rdf:resource=
	 * "http://www.semanticweb.org/123/ontologies/2015/2/tourism#XinBei"/>
	 * <hasName
	 * rdf:datatype="http://www.w3.org/2001/XMLSchema#string">白沙湾海水浴场</hasName>
	 * <hasStar rdf:datatype="http://www.w3.org/2001/XMLSchema#int">3</hasStar>
	 * <hasXCoordinate
	 * rdf:datatype="http://www.w3.org/2001/XMLSchema#double">121.57138889999999
	 * </hasXCoordinate> <hasYCoordinate
	 * rdf:datatype="http://www.w3.org/2001/XMLSchema#double">25.2686111</
	 * hasYCoordinate> <requireTime
	 * rdf:datatype="http://www.w3.org/2001/XMLSchema#int">4</requireTime>
	 * </owl:NamedIndividual>
	 */
		private static String ontology="";
		public static void startInstance(String name){
			ontology = "";
			String end = JdbcUtils.FrontToEnd(name);
			ontology+="<owl:NamedIndividual rdf:about=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#"+end+"\">"
			+"\r\n";
		}
		public static void writeCate(Set<String> set){
			if(set.size()==0){
				ontology+="<rdf:type rdf:resource=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#Attraction\"/>"
						+"\r\n";
			}
			else{
				Iterator<String> iter = set.iterator();
				while(iter.hasNext()){
					String item = iter.next();
					ontology+="<rdf:type rdf:resource=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#"+item+"\"/>"
							+"\r\n";
				}
			}
		}
		public static void writeAct(Set<String> set){
			Iterator<String> iter = set.iterator();
			while(iter.hasNext()){
				String item = iter.next();
				ontology+="<hasActivity rdf:resource=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#"+item+"\"/>"
						+"\r\n";
			}
		}
		public static void writePur(Set<String> set){
			Iterator<String> iter = set.iterator();
			while(iter.hasNext()){
				String item = iter.next();
				ontology+="<hasPurpose rdf:resource=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#"+item+"\"/>"
						+"\r\n";
			}
		}
		public static void writeBest(Set<String> set){
			Iterator<String> iter = set.iterator();
			while(iter.hasNext()){
				String item = iter.next();
				ontology+="<bestFor rdf:resource=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#"+item+"\"/>"
						+"\r\n";
			}
		}
		public static void writeName(String name){
			ontology+= "<hasName rdf:datatype=\"http://www.w3.org/2001/XMLSchema#string\">"+name+"</hasName>"
					+"\r\n";
		}
		public static void writeStar(int star){
			ontology+= "<hasStar rdf:datatype=\"http://www.w3.org/2001/XMLSchema#int\">"+star+"</hasStar>\r\n";
		}
		public static void writeTime(int time){
			ontology+= "<requireTime rdf:datatype=\"http://www.w3.org/2001/XMLSchema#int\">"+4+"</requireTime>\r\n";
		}
		public static void writeX(double x){
			ontology+="<hasXCoordinate rdf:datatype=\"http://www.w3.org/2001/XMLSchema#double\">"+x
					+"</hasXCoordinate>\r\n";
		}
		public static void writeY(double y){
			ontology+="<hasYCoordinate rdf:datatype=\"http://www.w3.org/2001/XMLSchema#double\">"+y
					+"</hasYCoordinate>\r\n";
		}
		public static void writeNearBy(List<String> list){
			for(int i=0;i<list.size();i++){
				String end = JdbcUtils.FrontToEnd(list.get(i));
				if(end==null){
					continue;
				}
				ontology+=" <isNear rdf:resource=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#"
			+end+"\"/>\r\n";
			}
		}
		public static void writeMatchWith(List<String> list){
			for(int i=0;i<list.size();i++){
				String end = JdbcUtils.FrontToEnd(list.get(i));
				if(end==null){
					continue;
				}
				ontology+=" <matchRoute rdf:resource=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#"
			+end+"\"/>\r\n";
			}
		}
		public static void writeSimilar(List<String> list){
			
			for(int i=0;i<list.size();i++){
				String end = JdbcUtils.FrontToEnd(list.get(i));
				if(end==null){
					continue;
				}
				ontology+=" <isSimilar rdf:resource=\"http://www.semanticweb.org/123/ontologies/2015/2/tourism#"
			+end+"\"/>\r\n";
			}
		}
		public static void endInstance(){
			ontology+="</owl:NamedIndividual>\r\n";
		}
		public static String getOntology(){
			return ontology;
		}
}
