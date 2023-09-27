package com.nokoriware.jarcweave.project.processed;

import com.nokoriware.jarcweave.project.Carrier;

public class Jumper extends Carrier {
	
	private Element element;

	public Jumper(String id) {
		super(id);
	}
	
	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

}
