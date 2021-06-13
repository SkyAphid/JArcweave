package com.nokoriware.nngine.arcweave.project.raw;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class RawAttribute extends Carrier {
	
	private String name;
	private RawAttributeValue value;

	public RawAttribute(String id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RawAttributeValue getValue() {
		return value;
	}

	public void setValue(RawAttributeValue value) {
		this.value = value;
	}
	
}