package com.nokoriware.nngine.arcweave.project.raw;

import java.util.ArrayList;

import com.nokoriware.nngine.arcweave.project.processed.Cover;
import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class RawArcweaveProject {

	private String name;
	private String startingElementID;
	
	private Cover cover;

	private ArrayList<RawBoard> boards = new ArrayList<>();

	private ArrayList<RawNote> notes = new ArrayList<>();
	private ArrayList<RawElement> elements = new ArrayList<>();
	private ArrayList<RawJumper> jumpers = new ArrayList<>();
	private ArrayList<RawConnection> connections = new ArrayList<>();

	private ArrayList<RawComponent> components = new ArrayList<>();
	private ArrayList<RawAttribute> attributes = new ArrayList<>();

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

	public ArrayList<RawBoard> getBoards() {
		return boards;
	}
	
	public RawBoard getBoard(String ID) {
		return (RawBoard) Carrier.getCarrier(boards, ID);
	}
	
	/*
	 * Notes
	 */
	

	public ArrayList<RawNote> getNotes() {
		return notes;
	}
	
	public RawNote getNote(String ID) {
		return (RawNote) Carrier.getCarrier(notes, ID);
	}
	
	/*
	 * Elements
	 */
	

	public ArrayList<RawElement> getElements() {
		return elements;
	}
	
	public RawElement getElement(String ID) {
		return (RawElement) Carrier.getCarrier(elements, ID);
	}
	
	/*
	 * Jumpers
	 */

	public ArrayList<RawJumper> getJumpers() {
		return jumpers;
	}
	
	public RawJumper getJumper(String ID) {
		return (RawJumper) Carrier.getCarrier(jumpers, ID);
	}
	
	/*
	 * Connections
	 */
	

	public ArrayList<RawConnection> getConnections() {
		return connections;
	}
	
	public RawConnection getConnection(String ID) {
		return (RawConnection) Carrier.getCarrier(connections, ID);
	}
	
	/*
	 * Components
	 */
	

	public ArrayList<RawComponent> getComponents() {
		return components;
	}
	
	public RawComponent getComponent(String ID) {
		return (RawComponent) Carrier.getCarrier(components, ID);
	}
	
	/*
	 * Attributes
	 */
	

	public ArrayList<RawAttribute> getAttributes() {
		return attributes;
	}
	
	public RawAttribute getAttribute(String ID) {
		return (RawAttribute) Carrier.getCarrier(attributes, ID);
	}
	

}
