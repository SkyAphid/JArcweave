package com.nokoriware.nngine.arcweave.project;

public class AttributeValue {
	
	private String[] data;

	public enum Type {
		STRING("string"),
		COMPONENT_LIST("component-list");
		
		private String typeName;
		
		public static final Type[] values = values();
		
		private Type(String typeName) {
			this.typeName = typeName;
		}
		
		/**
		 * Returns the string name of these enumerator values, which are present in the Arcweave JSON files.
		 */
		public String getTypeName() {
			return typeName;
		}
		
		/**
		 * Returns the Attribute Value Type enum based on its string name counterpart present in the Arcweave JSON files.
		 */
		public static Type get(String typeStringName) {
			for (int i = 0; i < values.length; i++) {
				if (values[i].getTypeName().equals(typeStringName)) {
					return values[i];
				}
			}
			
			return null;
		}
	}
	
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
