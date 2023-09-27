package com.nokoriware.jarcweave.project.raw;

import com.nokoriware.jarcweave.project.Carrier;

public class RawAttribute extends Carrier {
	
	//Despite the confusing naming scheme; these are referring to the parent object of this Attribute.
	private String cID; //The ID of the object (component or element) that this Attribute belongs to
	private String cType; //The type of object this attribute belongs to (component/element)
	private String name;
	private RawAttributeValue value;

	public RawAttribute(String id, String cID, String cType) {
		super(id);
		this.cID = cID;
		this.cType = cType;
	}
	
	public String getCID() {
		return cID;
	}
	
	public void setCID(String cID) {
		this.cID = cID;
	}
	
	public String getCType() {
		return cType;
	}
	
	public void setCType(String cType) {
		this.cType = cType;
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