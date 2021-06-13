package com.nokoriware.nngine.arcweave.project.processed;

import com.nokoriware.nngine.arcweave.project.raw.RawConnection.Type;
import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class Connection extends Carrier {
	
	private String label;
	private Element source;
	private Element target;

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

	public Element getSource() {
		return source;
	}

	public void setSource(Element source) {
		this.source = source;
	}

	public Element getTarget() {
		return target;
	}

	public void setTarget(Element target) {
		this.target = target;
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
