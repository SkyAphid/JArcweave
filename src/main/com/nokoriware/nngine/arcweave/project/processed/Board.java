package com.nokoriware.nngine.arcweave.project.processed;

import java.util.ArrayList;

import com.nokoriware.nngine.arcweave.project.util.Carrier;

public class Board extends Carrier {
	
	private String name;
	
	private ArrayList<Note> notes = new ArrayList<>();
	private ArrayList<Jumper> jumpers = new ArrayList<>();
	//TODO private ArrayList<Branch> branches = new ArrayList<>();
	private ArrayList<Element> elements = new ArrayList<>();
	private ArrayList<Connection> connections = new ArrayList<>();

	public Board(String id) {
		super(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
