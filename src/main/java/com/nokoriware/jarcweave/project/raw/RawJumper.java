package com.nokoriware.jarcweave.project.raw;

import com.nokoriware.jarcweave.project.Carrier;

public class RawJumper extends Carrier {
	
	private String elementID;

	public RawJumper(String id) {
		super(id);
	}

	public String getElementID() {
		return elementID;
	}

	public void setElementID(String elementID) {
		this.elementID = elementID;
	}
}
