package com.nokoriware.nngine.arcweave.project.processed;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class Jumper extends Carrier {
	
	private Element elementID;

	public Jumper(String id) {
		super(id);
	}
	
	public Element getElementID() {
		return elementID;
	}

	public void setElementID(Element elementID) {
		this.elementID = elementID;
	}

}
