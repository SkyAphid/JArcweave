package com.nokoriware.nngine.arcweave.project.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nokoriware.nngine.arcweave.project.processed.ArcweaveProject;
import com.nokoriware.nngine.arcweave.project.processed.Component;
import com.nokoriware.nngine.arcweave.project.processed.Element;

/**
 * This class is for reading various XML tags used in the content of Arcweave Project data.
 */
public class ArcweaveXMLUtility {
	
	/*
	 * Title links
	 */
	
	private static final String SPAN_START = "<span class=";
	private static final String SPAN_END = "<\\/span>";
	
	private static final String DATA_ID_START = "\" data-id=\"";
	private static final String DATA_ID_END = "\" data-type=\"component\">";

	/**
	 * Return a String with all paragraph tags removed from the given content.
	 */
	public static String removeParagraphTags(String content) {
		return content.replace("<p>", "").replaceAll("<\\/p>", "");
	}

	/**
	 * Parse the XML in the given element title to tag the linked components, and remove the XML code from the title in the process.
	 * 
	 * I don't want to add an actual XML parser to JArcweave unless I have to, so bear with me here.
	 */
	public static void linkElementTitleComponents(ArcweaveProject project, Element element) {

		while (element.getTitle().contains(SPAN_START)) {
			String title = element.getTitle();
			//System.err.println("Title: " + title);
			
			//Grab each instance of link code in the title
			int codeBeginIndex = regexIndexOf(SPAN_START, title);
			int codeEndIndex = regexIndexOf(SPAN_END, title) + SPAN_END.length();
			
			String code = title.substring(codeBeginIndex, codeEndIndex);
			
			//System.err.println("Code: " + code);
			
			//Pull out elementID
			int componentIDBeginIndex = regexIndexOf(DATA_ID_START, code) + DATA_ID_START.length();
			int componentIDEndIndex = regexIndexOf(DATA_ID_END, code);
			
			String componentID = code.substring(componentIDBeginIndex, componentIDEndIndex);

			//Add component to element
			Component component = project.getComponent(componentID);
			element.getTitleComponentLinkIDs().add(component);
			
			//System.err.println("Component: [" + componentID + "] " + (component != null ? component.getName() : "No component found"));
			
			//Lastly, remove the code from the title and clean it up
			int spanBeginIndex = codeBeginIndex;
			int spanEndIndex = title.indexOf(">", spanBeginIndex) + 1;
			String spanSubstring = title.substring(spanBeginIndex, spanEndIndex);

			//System.err.println("Span Substring: " + spanSubstring);
			
			String newTitle = title.replaceFirst(spanSubstring, "").replaceFirst(SPAN_END, "");
			element.setTitle(newTitle);
			//System.err.println("Result: " + element.getTitle() + "\n");
		}
		
	}
	
	private static int regexIndexOf(String regex, String string) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		
		if (m.find()) {
		   return m.start();
		}
		
		return -1;
	}
}
