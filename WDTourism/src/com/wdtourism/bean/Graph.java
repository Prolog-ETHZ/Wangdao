package com.wdtourism.bean;

import java.util.HashMap;
import java.util.Map;

public class Graph {
   private Map<String,Node> nodes;
   private Map<String,Link> links;
/**
 * @param nodes the nodes to set
 */
public void setNodes(Map<String,Node> nodes) {
	this.nodes = nodes;
}
/**
 * @return the nodes
 */
public Map<String,Node> getNodes() {
	return nodes;
}
/**
 * @param links the links to set
 */
public void setLinks(Map<String,Link> links) {
	this.links = links;
}
/**
 * @return the links
 */
public Map<String,Link> getLinks() {
	return links;
}
public Graph addNode(String id,Node n){
	if(nodes==null){
	   nodes = new HashMap<String,Node>();
	}
	nodes.put(id, n);
	return this;
}
public Graph addLink(String id,Link l){
	if(l==null){
		links = new HashMap<String,Link>();
	}
	links.put(id, l);
	return this;
}
}
