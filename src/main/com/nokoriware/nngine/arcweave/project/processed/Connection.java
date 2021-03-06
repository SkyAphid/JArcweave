package com.nokoriware.nngine.arcweave.project.processed;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class Connection extends Carrier {
	
	public enum Type {
		ELEMENTS("elements"),
		JUMPERS("jumpers");
		
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
		 * Returns the Connection Type enum based on its string name counterpart present in the Arcweave JSON files.
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

	private String label;
	
	private Element sourceElement;
	private Element targetElement;

	private Connection.Type sourceType;
	private Connection.Type targetType;

	public Connection(String id) {
		super(id);
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Element getSourceElement() {
		return sourceElement;
	}

	
	public void setSourceElement(Element source) {
		this.sourceElement = source;
	}

	public Element getTargetElement() {
		return targetElement;
	}

	public void setTargetElement(Element target) {
		this.targetElement = target;
	}
	
	public Connection.Type getSourceType() {
		return sourceType;
	}

	public void setSourceType(Connection.Type sourceType) {
		this.sourceType = sourceType;
	}

	public Connection.Type getTargetType() {
		return targetType;
	}

	public void setTargetType(Connection.Type targetType) {
		this.targetType = targetType;
	}

}
