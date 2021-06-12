package com.nokoriware.nngine.arcweave.project;

public class Attribute extends Carrier {
	
	private String name;
	private AttributeValue value;

	public Attribute(String id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttributeValue getValue() {
		return value;
	}

	public void setValue(AttributeValue value) {
		this.value = value;
	}
	
}