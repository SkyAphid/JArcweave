package com.nokoriware.nngine.arcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

/**
 * This is a fully processed Arcweave Project. Instead of everything being tied to IDs, all data has been tied to actual Java objects instead with proper references.
 * Thanks to this, working on a project with an end-user in mind is much easier.
 */
public class ArcweaveProject {

	private String name;
	private Element startingElement;
	
	private Cover cover;

	private ArrayList<Board> boards = new ArrayList<>();

	private ArrayList<Note> notes = new ArrayList<>();
	private ArrayList<Element> elements = new ArrayList<>();
	private ArrayList<Jumper> jumpers = new ArrayList<>();
	private ArrayList<Connection> connections = new ArrayList<>();

	private ArrayList<Component> components = new ArrayList<>();
	private ArrayList<Attribute> attributes = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Element getStartingElement() {
		return startingElement;
	}

	public void setStartingElement(Element startingElement) {
		this.startingElement = startingElement;
	}
	
	/*
	 * Cover
	 */
	
	public Cover getCover() {
		return cover;
	}

	public void setCover(Cover cover) {
		this.cover = cover;
	}
	
	/*
	 * Boards
	 */

	public ArrayList<Board> getBoards() {
		return boards;
	}
	
	public Board getBoard(String ID) {
		return (Board) Carrier.getCarrier(boards, ID);
	}
	
	/*
	 * Notes
	 */
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public Note getNote(String ID) {
		return (Note) Carrier.getCarrier(notes, ID);
	}
	
	
	/*
	 * Elements
	 */

	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public Element getElement(String ID) {
		return (Element) Carrier.getCarrier(elements, ID);
	}
	
	/*
	 * Jumpers
	 */

	public ArrayList<Jumper> getJumpers() {
		return jumpers;
	}
	
	public Jumper getJumper(String ID) {
		return (Jumper) Carrier.getCarrier(jumpers, ID);
	}
	
	/*
	 * Connections
	 */

	public ArrayList<Connection> getConnections() {
		return connections;
	}
	
	public Connection getConnection(String ID) {
		return (Connection) Carrier.getCarrier(connections, ID);
	}
	
	/*
	 * Components
	 */
	
	public ArrayList<Component> getComponents() {
		return components;
	}
	
	public Component getComponent(String ID) {
		return (Component) Carrier.getCarrier(components, ID);
	}
	
	/*
	 * Attributes
	 */
	
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	
	public Attribute getAttribute(String ID) {
		return (Attribute) Carrier.getCarrier(attributes, ID);
	}

}
