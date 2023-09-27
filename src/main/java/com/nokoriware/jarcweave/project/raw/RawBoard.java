package com.nokoriware.jarcweave.project.raw;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.Carrier;

public class RawBoard extends Carrier {
	
	private String name;
	
	private ArrayList<String> noteIDs = new ArrayList<>();
	private ArrayList<String> jumperIDs = new ArrayList<>();
	private ArrayList<String> branchIDs = new ArrayList<>();
	private ArrayList<String> elementIDs = new ArrayList<>();
	private ArrayList<String> connectionIDs = new ArrayList<>();
	
	public RawBoard(String id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * IDs - these are stored permanently
	 */
	
	public ArrayList<String> getNoteIDs() {
		return noteIDs;
	}
	
	public ArrayList<String> getJumperIDs() {
		return jumperIDs;
	}
	
	public ArrayList<String> getBranchIDs() {
		return branchIDs;
	}

	public ArrayList<String> getElementIDs() {
		return elementIDs;
	}
	
	public ArrayList<String> getConnectionIDs() {
		return connectionIDs;
	}

}
