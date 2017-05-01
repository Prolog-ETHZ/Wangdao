package com.wdtourism.bean;

public class Link extends Entity<Link>{
   private String from;
   private String to;
/**
 * @param from the from to set
 */
public void setFrom(String from) {
	this.from = from;
}
/**
 * @return the from
 */
public String getFrom() {
	return from;
}
/**
 * @param to the to to set
 */
public void setTo(String to) {
	this.to = to;
}
/**
 * @return the to
 */
public String getTo() {
	return to;
}
@Override
protected Link self() {
	// TODO Auto-generated method stub
	return this;
}
}
