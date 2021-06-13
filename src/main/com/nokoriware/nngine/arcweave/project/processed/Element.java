package com.nokoriware.nngine.arcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class Element extends Carrier {
	
	private String title;
	private String content;
	
	private ArrayList<Connection> connections = new ArrayList<>();
	private ArrayList<Component> components = new ArrayList<>();

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

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<Connection> connections) {
		this.connections = connections;
	}

	public ArrayList<Component> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}

}
