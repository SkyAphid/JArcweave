package com.nokoriware.nngine.arcweave.project.util;

import java.util.ArrayList;

/**
 * This class identifies and adds basic ID functionality to all classes that carry data within an Arcweave Project.
 */
public abstract class Carrier {
	
	protected String id;
	
	public Carrier(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	/**
	 * Returns a <code>Carrier</code> from a list of <code>Carrier</code> objects that has the given ID. 
	 * <br><br>
	 * Example: <code>getCarrier(getNotes(), "id");</code>
	 */
	public static Carrier getCarrier(ArrayList<? extends Carrier> carriers, String ID){
		for (int i = 0; i < carriers.size(); i++) {
			if (carriers.get(i).getID().equals(ID)) {
				return carriers.get(i);
			}
		}
		
		return null;
	}
}
