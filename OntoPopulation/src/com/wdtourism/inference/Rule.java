package com.wdtourism.inference;



public class Rule {
	private String[] head;
	private String[] tail;
	public Rule(String[] head,String[]tail){
		this.head = head;
		this.tail = tail;
	}
	/**
	 * @return the head
	 */
	public String[] getHead() {
		return head;
	}
	/**
	 * @param head the head to set
	 */
	public void setHead(String[] head) {
		this.head = head;
	}
	/**
	 * @return the tail
	 */
	public String[] getTail() {
		return tail;
	}
	/**
	 * @param tail the tail to set
	 */
	public void setTail(String[] tail) {
		this.tail = tail;
	}
	
}
