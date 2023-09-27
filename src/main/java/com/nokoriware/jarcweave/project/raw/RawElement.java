package com.nokoriware.jarcweave.project.raw;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.Carrier;

public class RawElement extends Carrier {
	
	private String title;
	private String content;
	
	private ArrayList<String> connectionOutputIDs = new ArrayList<>();
	private ArrayList<String> attributeIDs = new ArrayList<>();
	private ArrayList<String> componentIDs = new ArrayList<>();

	private String linkedBoardID;

	public RawElement(String id) {
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

	public ArrayList<String> getConnectionOutputIDs() {
		return connectionOutputIDs;
	}
	
	public ArrayList<String> getAttributeIDs(){
		return attributeIDs;
	}

	public ArrayList<String> getComponentIDs() {
		return componentIDs;
	}

	public String getLinkedBoardID() {
		return linkedBoardID;
	}

	public void setLinkedBoardID(String linkedBoardID) {
		this.linkedBoardID = linkedBoardID;
	}
	
}
