package com.nokoriware.jarcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.NamedCarrier;

public class Component extends NamedCarrier {
	
	private ArrayList<Attribute> attributes = new ArrayList<>();
	
	private boolean isFolder;
	private boolean isRoot;
	private ArrayList<Component> children = new ArrayList<>();

	public Component(String id) {
		super(id);
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	
	/**
	 * @param name - the name of the attribute to retrieve
	 * @return the first occurring attribute with the given name
	 */
	public Attribute getAttributeByName(String name) {
		return (Attribute) getCarrierByName(attributes, name);
	}
	
	/*
	 * Folder
	 */
	
	public boolean isFolder() {
		return isFolder;
	}
	
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public ArrayList<Component> getChildren() {
		return children;
	}

	/**
	 * @param name - the name of the child component to retrieve
	 * @return the first occurring child component with the given name
	 */
	public Component getChildByName(String name) {
		return (Component) getCarrierByName(children, name);
	}
	

}
