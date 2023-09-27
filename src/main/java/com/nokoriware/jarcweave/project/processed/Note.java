package com.nokoriware.jarcweave.project.processed;

import com.nokoriware.jarcweave.project.Carrier;

public class Note extends Carrier {
	
	private Content content;
	
	public Note(String id) {
		super(id);
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	
}
