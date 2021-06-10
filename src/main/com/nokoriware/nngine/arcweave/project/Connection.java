package com.nokoriware.nngine.arcweave.project;

public class Connection extends Carrier {
	
	private String label;
	private String sourceID;
	private String targetID;

	public enum Type {
		ELEMENTS("elements"),
		JUMPERS("jumpers");
		
		private String typeName;
		
		public static final Type[] values = Type.values();
		
		private Type(String typeName) {
			this.typeName = typeName;
		}
		
		public String getTypeName() {
			return typeName;
		}
		
		public static Type checkType(String type) {
			for (int i = 0; i < values.length; i++) {
				if (values[i].getTypeName().equals(type)) {
					return values[i];
				}
			}
			
			return null;
		}
	}
	
	private Type sourceType;
	private Type targetType;

	public Connection(String id) {
		super(id);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSourceID() {
		return sourceID;
	}

	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}

	public String getTargetID() {
		return targetID;
	}

	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}

	public Type getSourceType() {
		return sourceType;
	}

	public void setSourceType(Type sourceType) {
		this.sourceType = sourceType;
	}

	public Type getTargetType() {
		return targetType;
	}

	public void setTargetType(Type targetType) {
		this.targetType = targetType;
	}

	
}
