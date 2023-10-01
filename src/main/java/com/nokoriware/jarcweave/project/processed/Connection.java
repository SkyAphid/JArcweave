package com.nokoriware.jarcweave.project.processed;

import com.nokoriware.jarcweave.project.Carrier;

public class Connection extends Carrier {
	
	public enum ConnectionType {
		ELEMENTS("elements"),
		JUMPERS("jumpers");
		
		private String typeName;
		
		public static final ConnectionType[] values = values();
		
		private ConnectionType(String typeName) {
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
		public static ConnectionType get(String typeStringName) {
			for (int i = 0; i < values.length; i++) {
				if (values[i].getTypeName().equals(typeStringName)) {
					return values[i];
				}
			}
			
			return null;
		}
	}

	private Content label;
	
	private Element sourceElement;
	private Element targetElement;

	private Connection.ConnectionType sourceType;
	private Connection.ConnectionType targetType;

	public Connection(String id) {
		super(id);
	}
	
	public Content getLabel() {
		return label;
	}

	public void setLabel(Content label) {
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
	
	public Connection.ConnectionType getSourceType() {
		return sourceType;
	}

	public void setSourceType(Connection.ConnectionType sourceType) {
		this.sourceType = sourceType;
	}

	public Connection.ConnectionType getTargetType() {
		return targetType;
	}

	public void setTargetType(Connection.ConnectionType targetType) {
		this.targetType = targetType;
	}

}
