package com.nokoriware.nngine.arcweave.project.raw;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class RawNote extends Carrier {
	
	private String content;

	public RawNote(String id) {
		super(id);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
