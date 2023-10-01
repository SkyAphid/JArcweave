package com.nokoriware.jarcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.NamedCarrier;

public class Board extends NamedCarrier {

	private ArrayList<Note> notes = new ArrayList<>();
	private ArrayList<Jumper> jumpers = new ArrayList<>();
	//TODO private ArrayList<Branch> branches = new ArrayList<>();
	private ArrayList<Element> elements = new ArrayList<>();
	private ArrayList<Connection> connections = new ArrayList<>();

	public Board(String id) {
		super(id);
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public ArrayList<Jumper> getJumpers() {
		return jumpers;
	}

	public ArrayList<Element> getElements() {
		return elements;
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	
}
