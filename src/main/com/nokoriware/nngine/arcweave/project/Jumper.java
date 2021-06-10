package com.nokoriware.nngine.arcweave.project;

public class Jumper extends Carrier {
	
	private String elementID;

	public Jumper(String id) {
		super(id);
	}

	public String getElementID() {
		return elementID;
	}

	public void setElementID(String elementID) {
		this.elementID = elementID;
	}
}
