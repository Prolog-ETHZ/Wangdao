package com.wdtourism.inference;

import java.util.Set;

public class SemiAttraction {
	private Set<String> actConcept;
	private Set<String> purConcept;
	private Set<String> cateConcept;
	private Set<String> bestConcept;
	private String name;
	public SemiAttraction(String name,Set<String> actConcept,Set<String> purConcept,Set<String> cateConcept,Set<String> bestConcept){
		this.setName(name);
		this.actConcept = actConcept;
		this.purConcept = purConcept;
		this.cateConcept = cateConcept;
		this.bestConcept = bestConcept;
	}
	public void addActConcept(String act){
		actConcept.add(act);
	}
	public void addPurConcept(String pur){
		purConcept.add(pur);
	}
	public void addCateConcept(String cate){
		cateConcept.add(cate);
	}
	public void addBestConcept(String best){
		bestConcept.add(best);
	}
	/**
	 * @return the actConcept
	 */
	public Set<String> getActConcept() {
		return actConcept;
	}
	/**
	 * @param actConcept the actConcept to set
	 */
	public void setActConcept(Set<String> actConcept) {
		this.actConcept = actConcept;
	}
	/**
	 * @return the purConcept
	 */
	public Set<String> getPurConcept() {
		return purConcept;
	}
	/**
	 * @param purConcept the purConcept to set
	 */
	public void setPurConcept(Set<String> purConcept) {
		this.purConcept = purConcept;
	}
	/**
	 * @return the cateConcept
	 */
	public Set<String> getCateConcept() {
		return cateConcept;
	}
	/**
	 * @param cateConcept the cateConcept to set
	 */
	public void setCateConcept(Set<String> cateConcept) {
		this.cateConcept = cateConcept;
	}
	/**
	 * @return the bestConcept
	 */
	public Set<String> getBestConcept() {
		return bestConcept;
	}
	/**
	 * @param bestConcept the bestConcept to set
	 */
	public void setBestConcept(Set<String> bestConcept) {
		this.bestConcept = bestConcept;
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
}
