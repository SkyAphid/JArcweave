package com.nokoriware.nngine.arcweave.project;

public class Note extends Carrier {
	
	private String content;

	public Note(String id) {
		super(id);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
