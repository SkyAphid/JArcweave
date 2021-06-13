package com.nokoriware.nngine.arcweave.project.raw;

import com.nokoriware.nngine.arcweave.project.processed.Connection.Type;
import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class RawConnection extends Carrier {
	
	private String label;
	private String sourceID;
	private String targetID;

	private Type sourceType;
	private Type targetType;

	public RawConnection(String id) {
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
