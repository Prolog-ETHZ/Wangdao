package com.wdtourism.bean;

import java.io.Serializable;
import java.util.List;

public class Route implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2295694993329264095L;
	private int level;
	private List<String> list;
	public Route(int level, List<String> list){
		this.level = level;
		this.list = list;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}
}
