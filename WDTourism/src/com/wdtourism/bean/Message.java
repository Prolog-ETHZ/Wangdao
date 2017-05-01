package com.wdtourism.bean;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4358101020407230612L;
	/**
	 * 
	 */
	
	private int type;
	private String val;
	public static final int SENTENCE=0;
	public static final int NEARBY=1;
	public static final int MATCHWITH=2;
	public static final int BESIMILIAR=3;
	public static final int GEO = 4;
	public Message(int type,String val){
		this.setType(type);
		this.setVal(val);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
}
