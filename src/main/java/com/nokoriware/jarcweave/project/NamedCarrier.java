package com.nokoriware.jarcweave.project;

import java.util.ArrayList;

public abstract class NamedCarrier extends Carrier {

	protected String name = "";
	
	public NamedCarrier(String id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static NamedCarrier getCarrierByName(ArrayList<? extends Carrier> carriers, String name){
		for (int i = 0; i < carriers.size(); i++) {
			
			if (carriers.get(i) instanceof NamedCarrier) {
				NamedCarrier c = (NamedCarrier) carriers.get(i);
				
				if (c.getName().equals(name)) {
					return c;
				}
			}

		}
		
		return null;
	}
}
