package com.nokoriware.jarcweave.project.processed;

public class AttributeValue {
	
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

	private Content content;
	private Component[] componentList;
	
	private AttributeValue.Type valueType;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public Component[] getComponentList() {
		return componentList;
	}

	public void setComponentList(Component[] componentList) {
		this.componentList = componentList;
	}

	public AttributeValue.Type getValueType() {
		return valueType;
	}

	public void setValueType(AttributeValue.Type valueType) {
		this.valueType = valueType;
	}
	
}
