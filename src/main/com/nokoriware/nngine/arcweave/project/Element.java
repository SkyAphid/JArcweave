package com.nokoriware.nngine.arcweave.project;

import java.util.ArrayList;

public class Element extends Carrier {
	
	private String title;
	private String content;
	
	private ArrayList<String> connectionIDs = new ArrayList<>();
	private ArrayList<String> componentIDs = new ArrayList<>();

	public Element(String id) {
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<String> getConnectionIDs() {
		return connectionIDs;
	}

	public ArrayList<String> getComponentIDs() {
		return componentIDs;
	}

}
