package com.nokoriware.jarcweave.project.raw;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.Carrier;

public class RawComponent extends Carrier {
	
	private String name;
	private boolean isRoot;
	
	private ArrayList<String> attributeIDs = new ArrayList<>();
	private ArrayList<String> childrenIDs = new ArrayList<>();

	public RawComponent(String id) {
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

	public ArrayList<String> getAttributeIDs() {
		return attributeIDs;
	}

	public ArrayList<String> getChildrenIDs() {
		return childrenIDs;
	}

}
