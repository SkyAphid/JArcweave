package com.nokoriware.jarcweave.project.raw;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.Carrier;

public class RawComponent extends Carrier {
	
	private String name;
	private boolean isFolder;
	
	private boolean isRoot;
	private ArrayList<String> childrenIDs = new ArrayList<>();
	private ArrayList<String> attributeIDs = new ArrayList<>();

	public RawComponent(String id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getAttributeIDs() {
		return attributeIDs;
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

	public ArrayList<String> getChildrenIDs() {
		return childrenIDs;
	}

}
