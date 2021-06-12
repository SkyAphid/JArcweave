package com.nokoriware.nngine.arcweave.project;

import java.util.ArrayList;

public class ArcweaveProject {

	private String name;
	private String startingElementID;
	
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

	public String getStartingElementID() {
		return startingElementID;
	}

	public void setStartingElementID(String startingElementID) {
		this.startingElementID = startingElementID;
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
		return (Board) getCarrier(boards, ID);
	}
	
	/*
	 * Notes
	 */
	

	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public Note getNote(String ID) {
		return (Note) getCarrier(notes, ID);
	}
	
	/*
	 * Elements
	 */
	

	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public Element getElement(String ID) {
		return (Element) getCarrier(elements, ID);
	}
	
	/*
	 * Jumpers
	 */

	public ArrayList<Jumper> getJumpers() {
		return jumpers;
	}
	
	public Jumper getJumper(String ID) {
		return (Jumper) getCarrier(jumpers, ID);
	}
	
	/*
	 * Connections
	 */
	

	public ArrayList<Connection> getConnections() {
		return connections;
	}
	
	public Connection getConnection(String ID) {
		return (Connection) getCarrier(connections, ID);
	}
	
	/*
	 * Components
	 */
	

	public ArrayList<Component> getComponents() {
		return components;
	}
	
	public Component getComponent(String ID) {
		return (Component) getCarrier(components, ID);
	}
	
	/*
	 * Attributes
	 */
	

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	
	public Attribute getAttribute(String ID) {
		return (Attribute) getCarrier(attributes, ID);
	}
	
	/**
	 * Returns a <code>Carrier</code> from a list of <code>Carrier</code> objects that has the given ID. 
	 * <br><br>
	 * Example: <code>getCarrier(getNotes(), "id");</code>
	 */
	public Carrier getCarrier(ArrayList<? extends Carrier> carriers, String ID){
		for (int i = 0; i < carriers.size(); i++) {
			if (carriers.get(i).getID().equals(ID)) {
				return carriers.get(i);
			}
		}
		
		return null;
	}
}
