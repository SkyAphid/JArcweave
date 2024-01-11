package com.nokoriware.jarcweave.project.processed;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Content {
	
	private Document document;
	
	private ArrayList<ComponentMention> mentions;
	
	public Content(Document document) {
		this.document = document;
		document.outputSettings().prettyPrint(false);
		
		mentions = new ArrayList<ComponentMention>();
		
		for (int i = 0; i < document.getAllElements().size(); i++) {
			Element e = document.getAllElements().get(i);
			
			//If the element has a data-id, it means that it's linked to somewhere in the Arcweave project. We want to isolate that.
			if (e.hasAttr("data-id")) {
				ComponentMention mention = new ComponentMention(e.text(), e.attr("data-id"));
				mentions.add(mention);
			}

		}
	}
	
	public Content(String string) {
		this(Jsoup.parse(string));
	}
	
	/**
	 * @return the HTML document underlying this object, parsed with JSoup.
	 */
	public Document getDocument() {
		return document;
	}
	
	/**
	 * @return the document housed in this object as a parsed, combined, normalized string.
	 */
	public String getText() {
		return document.text();
	}
	
	/**
	 * Processes the text of each paragraph in the document into an array, with their html tags intact so that string modifiers like italics, bold, and so on can still be processed.
	 * 
	 * @return the processed paragraphs array
	 */
	public String[] getParagraphHTML() {

	    Elements paragraphs = document.select("p");
	    String[] processed = new String[paragraphs.size()];
	    
	    for (int i = 0; i < paragraphs.size(); i++) {
	    	//System.err.println(i + " " + paragraphs.get(i).html());
	    	processed[i] = paragraphs.get(i).html();
	    }
	    
	    return processed;
	}
	
	/**
	 * @return true if the text of this element is blank. (<code>getText().isBlank()</code>)
	 */
	public boolean isBlank() {
		return getText().isBlank();
	}

	/**
	 * Overriding toString() allows Content to functionally work as a String when it comes to being presented in text-boxes.
	 */
	@Override
	public String toString() {
		return document.text();
	}

	/**
	 * @return all ComponentMention objects in this Content object that have a corresponding label.
	 */
	public ArrayList<ComponentMention> getComponentMentions(String dataLabel) {
		ArrayList<ComponentMention> found = new ArrayList<>();
		
		for (int i = 0; i < mentions.size(); i++) {
			ComponentMention d = mentions.get(i);
			
			if (d.dataLabel.equals(dataLabel)) {
				found.add(d);
			}
		}
		
		return found;
	}
	
	public ComponentMention[] getAllComponentMentions() {
		return mentions.toArray(ComponentMention[]::new);
	}

	public void linkComponentMentions(ArcweaveProject project) {
		for (ComponentMention mention : mentions) {
			mention.linkedComponent = project.getComponent(mention.dataID);
		}
	}
	
	/**
	 * A reference to another component in the Arcweave project; we couple it with the text it was linked to, along with the dataID itself for what it's referencing.
	 */
	public static class ComponentMention {
		private String dataLabel;
		private String dataID;
		private Component linkedComponent;
		
		public ComponentMention(String dataLabel, String dataID) {
			this.dataLabel = dataLabel;
			this.dataID = dataID;
		}
		
		public String getDataLabel() {
			return dataLabel;
		}
		
		public String getDataID() {
			return dataID;
		}
		
		public Component getLinkedComponent() {
			return linkedComponent;
		}

	};
}
