package com.nokoriware.jarcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.Carrier;

public class Component extends Carrier {
	
	private String name;
	private boolean isRoot;
	
	private ArrayList<Attribute> attributes = new ArrayList<>();
	private ArrayList<Component> children = new ArrayList<>();

	public Component(String id) {
		super(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public ArrayList<Component> getChildren() {
		return children;
	}

	

}
