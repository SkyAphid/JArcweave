package com.nokoriware.jarcweave.project.processed;

public class AttributeValue {
	
	public enum ValueType {
		STRING("string"),
		COMPONENT_LIST("component-list");
		
		private String typeName;
		
		public static final ValueType[] values = values();
		
		private ValueType(String typeName) {
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
		public static ValueType get(String typeStringName) {
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
	
	private AttributeValue.ValueType valueType;

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

	public AttributeValue.ValueType getValueType() {
		return valueType;
	}

	public void setValueType(AttributeValue.ValueType valueType) {
		this.valueType = valueType;
	}
	
}
