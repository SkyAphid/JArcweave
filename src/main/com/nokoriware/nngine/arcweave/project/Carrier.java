package com.nokoriware.nngine.arcweave.project;

/**
 * This class identifies and adds basic ID functionality to all classes that carry data within a <code>Project</code>.
 */
public abstract class Carrier {
	
	protected String id;
	
	public Carrier(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
	
}
