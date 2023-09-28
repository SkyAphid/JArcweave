package com.nokoriware.jarcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.Carrier;

public class Component extends Carrier {
	
	private String name;
	private ArrayList<Attribute> attributes = new ArrayList<>();
	
	private boolean isFolder;
	private boolean isRoot;
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
	
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	
	/*
	 * Folder
	 */
	
	public boolean isFolder() {
		return isFolder;
	}
	
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public ArrayList<Component> getChildren() {
		return children;
	}

	

}
