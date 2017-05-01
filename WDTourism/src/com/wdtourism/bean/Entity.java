package com.wdtourism.bean;

public abstract class Entity<T extends Entity<?>> {
  private String name;
  private String type;
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}
/**
 * @return the type
 */
public String getType() {
	return type;
}
protected abstract T self();
  
}
