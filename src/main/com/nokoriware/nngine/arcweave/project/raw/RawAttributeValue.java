package com.nokoriware.nngine.arcweave.project.raw;

import com.nokoriware.nngine.arcweave.project.processed.AttributeValue.Type;

public class RawAttributeValue {
	
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
