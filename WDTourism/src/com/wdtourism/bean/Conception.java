package com.wdtourism.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Conception implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 5172097754362279169L;
private String name;
  private String description;
  private int type;
  public final static int ATTRACTION =1;
  public final static int ACTIVITY = 2;
  public final static int PLACE =3;
  public final static int PURPOSE = 4;
  public final static int TAG = 5;
  public final static int TIME =6;
  public final static int ACCOMPANY = 7;
  private ArrayList<String> belongs;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

/**
 * @return the belongs
 */
public ArrayList<String> getBelongs() {
	return belongs;
}
/**
 * @param belongs the belongs to set
 */
public void setBelongs(ArrayList<String> belongs) {
	this.belongs = belongs;
}
/**
 * @return the type
 */
public int getType() {
	return type;
}
/**
 * @param type the type to set
 */
public void setType(int type) {
	this.type = type;
}


}
