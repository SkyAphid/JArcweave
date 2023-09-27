package com.nokoriware.jarcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.Carrier;

public class Element extends Carrier {
	
	private Content title;
	private Content content;
	
	private ArrayList<Connection> connectionOutputs = new ArrayList<>();
	private ArrayList<Attribute> attributes = new ArrayList<>();
	private ArrayList<Component> components = new ArrayList<>();
	
	private Board linkedBoard;

	public Element(String id) {
		super(id);
	}
	
	public Content getTitle() {
		return title;
	}

	public void setTitle(Content title) {
		this.title = title;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public ArrayList<Connection> getConnectionOutputs() {
		return connectionOutputs;
	}
	
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	
	public ArrayList<Component> getComponents() {
		return components;
	}

	public Board getLinkedBoard() {
		return linkedBoard;
	}

	public void setLinkedBoard(Board linkedBoard) {
		this.linkedBoard = linkedBoard;
	}

	/**
	 * Will return true or false based on whether or not any of the connections in this Element contain a label.
	 * This is useful in case you want to see if this element skips straight to the next element or if it expects a player response (where the response would be whatever the label is).
	 */
	public boolean connectionsContainLabels() {
		
		for (int i = 0; i < connectionOutputs.size(); i++) {
			Connection connection = connectionOutputs.get(i);
			
			if (!connection.getLabel().isBlank()) {
				return true;
			}
		}
		
		return false;
	}
}
