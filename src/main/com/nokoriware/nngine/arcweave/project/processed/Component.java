package com.nokoriware.nngine.arcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

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
