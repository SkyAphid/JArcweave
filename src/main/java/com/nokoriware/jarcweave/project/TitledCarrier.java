package com.nokoriware.jarcweave.project;

import java.util.ArrayList;

import com.nokoriware.jarcweave.project.processed.Content;

public abstract class TitledCarrier extends Carrier {

	protected Content title = null;
	
	public TitledCarrier(String id) {
		super(id);
	}

	public Content getTitle() {
		return title;
	}

	public void setTitle(Content title) {
		this.title = title;
	}
	
	public static TitledCarrier getCarrierByTitle(ArrayList<? extends Carrier> carriers, String title){
		for (int i = 0; i < carriers.size(); i++) {
			
			if (carriers.get(i) instanceof TitledCarrier) {
				TitledCarrier c = (TitledCarrier) carriers.get(i);
				
				if (c.getTitle().getText().equals(title)) {
					return c;
				}
			}

		}
		
		return null;
	}
}
