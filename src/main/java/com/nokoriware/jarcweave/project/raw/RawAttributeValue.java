package com.nokoriware.jarcweave.project.raw;

import com.nokoriware.jarcweave.project.processed.AttributeValue.ValueType;

public class RawAttributeValue {
	
	private String[] data;

	private ValueType valueType;

	public String[] getData() {
		return data;
	}

	public void setData(String... data) {
		this.data = data;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}
	
}
