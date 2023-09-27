package com.nokoriware.jarcweave.project.raw;

import com.nokoriware.jarcweave.project.Carrier;

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
