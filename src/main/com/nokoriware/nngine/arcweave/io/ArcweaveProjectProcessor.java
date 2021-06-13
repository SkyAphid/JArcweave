package com.nokoriware.nngine.arcweave.io;

import com.nokoriware.nngine.arcweave.project.raw.*;
import static com.nokoriware.nngine.arcweave.project.util.ArcweaveXMLUtility.*;

import java.util.ArrayList;

import com.nokoriware.nngine.arcweave.project.processed.*;

/**
 * This class will process RawArcweaveProjects and turn them into more user-friendly ArcweaveProjects.
 */
public class ArcweaveProjectProcessor {

	/*
	 * This function will process a <code>RawArcweaveProject</code> into a <code>ArcweaveProject</code>.
	 * <br>Processed ArcweaveProject's are connected by object references rather than IDs, making their usage much easier. However, both options are available to suit any particular usecase.
	 */
	public static ArcweaveProject process(RawArcweaveProject rawProject) {
		
		ArcweaveProject project = new ArcweaveProject();
		
		processBoards(rawProject, project);
		
		return project;
	}
	
	private static void processBoards(RawArcweaveProject rawProject, ArcweaveProject project) {
		firstPass(rawProject, project);
		secondPass(rawProject, project);
	}
	
	/*
	 * The first pass will create all of the objects in the project and copy their content, however it won't link the object references quite yet.
	 */
	private static void firstPass(RawArcweaveProject rawProject, ArcweaveProject project) {
		/*
		 * Boards
		 */
		
		ArrayList<RawBoard> rawBoards = rawProject.getBoards();
		
		for (int i = 0; i < rawBoards.size(); i++) {
			RawBoard rawBoard = rawBoards.get(i);
			
			Board board = new Board(rawBoard.getID());
			project.getBoards().add(board);
		}
		
		/*
		 * Notes
		 */
		
		ArrayList<RawNote> rawNotes = rawProject.getNotes();
		
		for (int i = 0; i < rawNotes.size(); i++) {
			RawNote rawNote = rawNotes.get(i);
			
			Note note = new Note(rawNote.getID());
			note.setContent(removeParagraphTags(rawNote.getContent()));
			
			project.getNotes().add(note);
		}
		
		/*
		 * Elements
		 */
		
		ArrayList<RawElement> rawElements = rawProject.getElements();
		
		for (int i = 0; i < rawElements.size(); i++) {
			RawElement rawElement = rawElements.get(i);
			
			Element element = new Element(rawElement.getID());
			element.setTitle(removeParagraphTags(rawElement.getTitle()));
			element.setContent(removeParagraphTags(rawElement.getContent()));
			
			project.getElements().add(element);
		}
		
		/*
		 * Jumpers
		 */
		
		ArrayList<RawJumper> rawJumpers = rawProject.getJumpers();
		
		for (int i = 0; i < rawJumpers.size(); i++) {
			RawJumper rawJumper = rawJumpers.get(i);
			
			Jumper jumper = new Jumper(rawJumper.getID());
			project.getJumpers().add(jumper);
		}
		
		/*
		 * Connections
		 */
		
		ArrayList<RawConnection> rawConnections = rawProject.getConnections();
		
		for (int i = 0; i < rawConnections.size(); i++) {
			RawConnection rawConnection = rawConnections.get(i);
			
			Connection connection = new Connection(rawConnection.getID());
			
			connection.setLabel(removeParagraphTags(rawConnection.getLabel()));
			
			project.getConnections().add(connection);
		}
		
		/*
		 * Components
		 */
		
		ArrayList<RawComponent> rawComponents = rawProject.getComponents();
		
		for (int i = 0; i < rawComponents.size(); i++) {
			RawComponent rawComponent = rawComponents.get(i);
			
			Component component = new Component(rawComponent.getID());
			component.setName(rawComponent.getName());
			component.setRoot(rawComponent.isRoot());
		}
		
		/*
		 * Attributes
		 */
		
		ArrayList<RawAttribute> rawAttributes = rawProject.getAttributes();
		
		for (int i = 0; i < rawAttributes.size(); i++) {
			//Attribute
			RawAttribute rawAttribute = rawAttributes.get(i);
			
			Attribute attribute = new Attribute(rawAttribute.getID());
			attribute.setName(removeParagraphTags(rawAttribute.getName()));
			
			//Value
			RawAttributeValue rawValue = rawAttribute.getValue();
			
			AttributeValue value = new AttributeValue();
			value.setValueType(rawValue.getValueType());
			
			attribute.setValue(value);
		}
		
		/*
		 * TODO Branches
		 */
		
		/*
		 * TODO Variables
		 */
		
		/*
		 * TODO Conditions
		 */
	}

	/*
	 * The second pass will begin searching the projects and linking the various data together with true object references, allowing their usage to be much simpler in the end.
	 */
	private static void secondPass(RawArcweaveProject rawProject, ArcweaveProject project) {
		/*
		 * Boards
		 */
		
		ArrayList<Board> boards = project.getBoards();
		
		for (int i = 0; i < boards.size(); i++) {
			Board board = boards.get(i);
			
			//Notes
			
			
			//Jumpers
			
			//TODO Branches
			
			//Elements
			
			//Connections
		}
		
		/*
		 * Notes
		 */
		
		
		
		/*
		 * Elements
		 */
		
		
		
		/*
		 * Jumpers
		 */
		
		

		/*
		 * Connections
		 */
		
		
		
		/*
		 * Components
		 */
		
		
		
		/*
		 * Attributes
		 */
		
		
		
		/*
		 * TODO Branches
		 */
		
		/*
		 * TODO Variables
		 */
		
		/*
		 * TODO Conditions
		 */
	}
}
