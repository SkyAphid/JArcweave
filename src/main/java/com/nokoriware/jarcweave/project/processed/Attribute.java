package com.nokoriware.jarcweave.project.processed;

import com.nokoriware.jarcweave.project.Carrier;

public class Attribute extends Carrier {

	public enum CType {
		COMPONENTS("components"),
		ELEMENTS("elements");
		
		public static final CType[] values = values();
		
		private String id;
		private CType(String id) {
			this.id = id;
		}
		
		public static CType get(String cType) {
			for (CType c : values) {
				if (c.id.equals(cType)) {
					return c;
				}
			}
			
			return null;
		}
	}
	
	private String cID;
	private CType cType;
	private String name;
	private AttributeValue value;
	
	//Will be either a Component or Element
	private Carrier parent;

	public Attribute(String id, String cID, CType cType) {
		super(id);
		this.cID = cID;
		this.cType = cType;
	}
	
	public Attribute(String id, String cID, String cType) {
		this(id, cID, CType.get(cType));
	}
	
	public String getCID() {
		return cID;
	}
	
	public void setCID(String cID) {
		this.cID = cID;
	}
	
	public CType getCType() {
		return cType;
	}
	
	public void setCType(CType cType) {
		this.cType = cType;
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
	
	public Carrier getParent() {
		return parent;
	}
	
	public void setParent(Carrier parent) {
		this.parent = parent;
	}
	
}