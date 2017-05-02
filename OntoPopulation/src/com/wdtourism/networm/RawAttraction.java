package com.wdtourism.networm;

import java.util.ArrayList;

public class RawAttraction {
	private String name;
	private int star;
	private int requireTime;
	private ArrayList<String> desList;
	private String intro;
	public RawAttraction(String name,int star,int requireTime,ArrayList<String> desList,String intro){
		this.name = name;
		this.star = star;
		this.requireTime = requireTime;
		this.desList = desList;
		this.intro = intro;
	}
	public RawAttraction(){
		this(null,0,0,null,null);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the star
	 */
	public int getStar() {
		return star;
	}
	/**
	 * @param star the star to set
	 */
	public void setStar(int star) {
		this.star = star;
	}
	/**
	 * @return the requireTime
	 */
	public int getRequireTime() {
		return requireTime;
	}
	/**
	 * @param requireTime the requireTime to set
	 */
	public void setRequireTime(int requireTime) {
		this.requireTime = requireTime;
	}
	/**
	 * @return the desList
	 */
	public ArrayList<String> getDesList() {
		return desList;
	}
	/**
	 * @param desList the desList to set
	 */
	public void setDesList(ArrayList<String> desList) {
		this.desList = desList;
	}
	/**
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}
	/**
	 * @param intro the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
}