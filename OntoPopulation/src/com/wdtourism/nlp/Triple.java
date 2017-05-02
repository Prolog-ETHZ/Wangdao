package com.wdtourism.nlp;

public class Triple {
// bean
	private String obj;//客体，第二个
	private String sub;//主体，第一个
	private String rel;//关系
	public Triple(String obj,String sub,String rel){
		this.setObj(obj);
		this.setSub(sub);
		this.setRel(rel);
	}
	/**
	 * @return the obj
	 */
	public String getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(String obj) {
		this.obj = obj;
	}
	/**
	 * @return the sub
	 */
	public String getSub() {
		return sub;
	}
	/**
	 * @param sub the sub to set
	 */
	public void setSub(String sub) {
		this.sub = sub;
	}
	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}
	/**
	 * @param rel the rel to set
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}
}
