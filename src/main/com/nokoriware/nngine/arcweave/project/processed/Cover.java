package com.nokoriware.nngine.arcweave.project.processed;

public class Cover {
	private String fileName;
	
	//This list may be incomplete, I'm unsure of what other covers you can have besides a cover image.
	public enum Type {
		COVER_IMAGE("cover-image");
		
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
		 * Returns the Cover Type enum based on its string name counterpart present in the Arcweave JSON files.
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
	
	private Type type;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
