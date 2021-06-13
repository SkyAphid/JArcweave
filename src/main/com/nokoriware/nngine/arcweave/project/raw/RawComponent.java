package com.nokoriware.nngine.arcweave.project.raw;

import java.util.ArrayList;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

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
