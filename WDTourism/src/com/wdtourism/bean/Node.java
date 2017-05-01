package com.wdtourism.bean;

import java.util.HashMap;
import java.util.Map;

public class Node extends Entity<Node>{
   private Map<String,String> actions;
   public Map<String,String> getActions(){
	   return actions;
   }
   public Node setAction(Map<String,String> actions){
	   this.actions = actions;
	   return this;
   }
   public Node addAction(String action,String title){
	   if(actions == null){
		   actions = new HashMap<String,String>();
	   }
	   actions.put(action, title);
	   return this;
   }
@Override
protected Node self() {
	// TODO Auto-generated method stub
	return this;
}
   
   
   
}
