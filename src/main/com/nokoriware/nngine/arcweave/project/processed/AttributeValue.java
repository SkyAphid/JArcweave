package com.nokoriware.nngine.arcweave.project.processed;

import com.nokoriware.nngine.arcweave.project.raw.RawAttributeValue.Type;

public class AttributeValue {
	
	private String[] data;

	private Type valueType;

	public String[] getData() {
		return data;
	}

	public void setData(String... data) {
		this.data = data;
	}

	public Type getValueType() {
		return valueType;
	}

	public void setValueType(Type valueType) {
		this.valueType = valueType;
	}
	
}
