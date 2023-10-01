package com.nokoriware.jarcweave.project.raw;

import com.nokoriware.jarcweave.project.Carrier;
import com.nokoriware.jarcweave.project.processed.Connection.ConnectionType;

public class RawConnection extends Carrier {
	
	private String label;
	private String sourceID;
	private String targetID;

	private ConnectionType sourceType;
	private ConnectionType targetType;

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

	public ConnectionType getSourceType() {
		return sourceType;
	}

	public void setSourceType(ConnectionType sourceType) {
		this.sourceType = sourceType;
	}

	public ConnectionType getTargetType() {
		return targetType;
	}

	public void setTargetType(ConnectionType targetType) {
		this.targetType = targetType;
	}

	
}
