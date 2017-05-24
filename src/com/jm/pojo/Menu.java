package com.jm.pojo;

import java.util.ArrayList;
import java.util.List;

public class Menu {
  
  private boolean leaf;
  private String text;
  private String controller;
  private String viewXtype;
  private boolean expanded;
  private List<Menu> children=null;
  
public boolean getLeaf() {
	return leaf;
}
public String getText() {
	return text;
}
public String getController() {
	return controller;
}

public List<Menu> getChildren() {
	return children;
}

public void setLeaf(boolean leaf) {
	this.leaf = leaf;
}
public void setText(String text) {
	this.text = text;
}
public void setController(String controller) {
	this.controller = controller;
}

public void setChildren(List<Menu> children) {
	this.children = children;
}
public boolean getExpanded() {
	return expanded;
}
public void setExpanded(boolean expanded) {
	this.expanded = expanded;
}
public Menu() {
	super();
	// TODO Auto-generated constructor stub
}
public Menu( String text, String controller, String viewXtype, List<Menu> children) {
	super();
	this.leaf = false;
	this.text = text;
	this.controller = controller;
	this.viewXtype = viewXtype;
	this.expanded = true;
	this.children = children;
}
public Menu( String text, String controller, String viewXtype) {
	super();
	this.leaf = true;
	this.text = text;
	this.controller = controller;
	this.viewXtype = viewXtype;
	this.expanded =false;
	
}
public String getViewXtype() {
	return viewXtype;
}
public void setViewXtype(String viewXtype) {
	this.viewXtype = viewXtype;
}

  


}
