package com.nokoriware.jarcweave.io;

import com.nokoriware.jarcweave.project.processed.*;
import com.nokoriware.jarcweave.project.raw.*;

import java.util.ArrayList;

/**
 * This class will process RawArcweaveProjects and turn them into more user-friendly ArcweaveProjects.
 */
public class ArcweaveProjectProcessor {

	/**
	 * This function will process a <code>RawArcweaveProject</code> into a <code>ArcweaveProject</code>.
	 * <br>Processed ArcweaveProject's are connected by object references rather than IDs, making their usage much easier. However, both options are available to suit any particular usecase.
	 */
	public static ArcweaveProject process(RawArcweaveProject rawProject) {
		
		ArcweaveProject project = new ArcweaveProject();
		
		processBoards(rawProject, project);
		
		return project;
	}
	
	private static void processBoards(RawArcweaveProject rawProject, ArcweaveProject project) {
		copyDataPass(rawProject, project);
		linkObjectsPass(rawProject, project);
	}
	
	/**
	 * This first pass will create all of the objects in the project and copy their content, adding them to the <code>ArcweaveProject</code>.
	 * However, none of these objects have their actual references added yet. That is reserved for the second pass.
	 */
	private static void copyDataPass(RawArcweaveProject rawProject, ArcweaveProject project) {
		/*
		 * General project information
		 */
		
		project.setName(rawProject.getName());
		project.setCover(rawProject.getCover());
		
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
			note.setContent(new Content(rawNote.getContent()));
			
			
			project.getNotes().add(note);
		}
		
		/*
		 * Elements
		 */
		
		ArrayList<RawElement> rawElements = rawProject.getElements();
		
		for (int i = 0; i < rawElements.size(); i++) {
			RawElement rawElement = rawElements.get(i);
			
			Element element = new Element(rawElement.getID());
			element.setTitle(new Content(rawElement.getTitle()));
			element.setContent(new Content(rawElement.getContent()));
			
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
			
			//Label
			connection.setLabel(new Content(rawConnection.getLabel()));
			
			//Source/target types
			connection.setSourceType(rawConnection.getSourceType());
			connection.setTargetType(rawConnection.getTargetType());
			
			project.getConnections().add(connection);
		}
		
		/*
		 * Components
		 */
		
		ArrayList<RawComponent> rawComponents = rawProject.getComponents();
		
		for (int i = 0; i < rawComponents.size(); i++) {
			RawComponent rawComponent = rawComponents.get(i);
			
			//copy data
			Component component = new Component(rawComponent.getID());
			component.setName(rawComponent.getName());
			component.setRoot(rawComponent.isRoot());
			component.setFolder(rawComponent.isFolder());
			
			//add to project
			project.getComponents().add(component);
		}
		
		/*
		 * Attributes
		 */
		
		ArrayList<RawAttribute> rawAttributes = rawProject.getAttributes();
		
		for (int i = 0; i < rawAttributes.size(); i++) {
			//Attribute
			RawAttribute rawAttribute = rawAttributes.get(i);
			
			Attribute attribute = new Attribute(rawAttribute.getID(), rawAttribute.getCID(), rawAttribute.getCType());

			//Name
			attribute.setName(rawAttribute.getName());
			
			//Value
			RawAttributeValue rawValue = rawAttribute.getValue();
			
			AttributeValue value = new AttributeValue();
			
			//System.out.println("2   " + rawAttribute.getID() + " " + Arrays.toString(rawValue.getData()));
			
			//Value Type
			AttributeValue.Type type = rawValue.getValueType();
			value.setValueType(type);
			
			//If the AttributeValue only contains string content, copy that data over. If references, save that for the second pass.
			if (type == AttributeValue.Type.STRING) {
				value.setContent(new Content(rawValue.getData()[0]));
			}
			
			attribute.setValue(value);
			
			//Add attribute to project
			project.getAttributes().add(attribute);
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

	/**
	 * This second pass will begin searching the projects and linking the various data together with true object references, allowing their usage to be much simpler for general programming.
	 * <br><br>Some elements, such as Notes, do not need the second pass because they do not contain references to other objects.
	 */
	private static void linkObjectsPass(RawArcweaveProject rawProject, ArcweaveProject project) {
		
		/*
		 * Starting Element
		 */
		
		project.setStartingElement(project.getElement(rawProject.getStartingElementID()));
		
		/*
		 * Boards
		 */
		
		ArrayList<Board> boards = project.getBoards();
		
		for (int i = 0; i < boards.size(); i++) {
			Board board = boards.get(i);
			RawBoard rawBoard = rawProject.getBoard(board.getID());
			
			//Notes
			ArrayList<String> rawNoteIDs = rawBoard.getNoteIDs();
			
			for (int j = 0; j < rawNoteIDs.size(); j++) {
				String noteID = rawNoteIDs.get(j);
				board.getNotes().add(project.getNote(noteID));
			}
			
			//Jumpers
			ArrayList<String> rawJumperIDs = rawBoard.getJumperIDs();
			
			for (int j = 0; j < rawJumperIDs.size(); j++) {
				String jumperID = rawJumperIDs.get(j);
				board.getJumpers().add(project.getJumper(jumperID));
			}
			
			//TODO Branches
			
			//Elements
			ArrayList<String> rawElementIDs = rawBoard.getElementIDs();
			
			for (int j = 0; j < rawElementIDs.size(); j++) {
				String elementID = rawElementIDs.get(j);
				board.getElements().add(project.getElement(elementID));
			}
			
			//Connections
			ArrayList<String> rawConnectionIDs = rawBoard.getConnectionIDs();
			
			for (int j = 0; j < rawConnectionIDs.size(); j++) {
				String connectionID = rawConnectionIDs.get(j);
				board.getConnections().add(project.getConnection(connectionID));
			}
		}
		
		/*
		 * Notes
		 */
		
		ArrayList<Note> notes = project.getNotes();
		
		for (int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			note.getContent().linkComponentMentions(project);
		}
		
		/*
		 * Elements
		 */
		
		ArrayList<Element> elements = project.getElements();
		
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			RawElement rawElement = rawProject.getElement(element.getID());
			
			//Title links
			element.getTitle().linkComponentMentions(project);
			element.getContent().linkComponentMentions(project);
			
			//Connection Outputs
			ArrayList<String> rawConnectionOutputIDs = rawElement.getConnectionOutputIDs();
			
			for (int j = 0; j < rawConnectionOutputIDs.size(); j++) {
				String connectionID = rawConnectionOutputIDs.get(j);
				Connection connection = project.getConnection(connectionID);
				
				element.getConnectionOutputs().add(connection);
			}
			
			//Attributes
			ArrayList<String> rawAttributeIDs = rawElement.getAttributeIDs();
			
			for (int j = 0; j < rawAttributeIDs.size(); j++) {
				String attributeID = rawAttributeIDs.get(j);
				element.getAttributes().add(project.getAttribute(attributeID));
			}
			
			//Components
			ArrayList<String> rawComponentIDs = rawElement.getComponentIDs();
			
			for (int j = 0; j < rawComponentIDs.size(); j++) {
				String componentID = rawComponentIDs.get(j);
				element.getComponents().add(project.getComponent(componentID));
			}
			
			//Linked Boards
			String linkedBoardID = rawElement.getLinkedBoardID();
			element.setLinkedBoard(project.getBoard(linkedBoardID));
		}
		
		/*
		 * Jumpers
		 */
		
		ArrayList<Jumper> jumpers = project.getJumpers();
		
		for (int i = 0; i < jumpers.size(); i++) {
			Jumper jumper = jumpers.get(i);
			RawJumper rawJumper = rawProject.getJumper(jumper.getID());
			
			//Element ID
			String elementID = rawJumper.getElementID();
			jumper.setElement(project.getElement(elementID));
		}

		/*
		 * Connections
		 */
		
		ArrayList<Connection> connections = project.getConnections();
		
		for (int i = 0; i < connections.size(); i++) {
			Connection connection = connections.get(i);
			RawConnection rawConnection = rawProject.getConnection(connection.getID());
			
			//System.err.println(connection.getSourceType() + " " + connection.getTargetType());
			
			//Link source elements
			if (connection.getSourceType() == Connection.Type.ELEMENTS) {
				String sourceElementID = rawConnection.getSourceID();
				connection.setSourceElement(project.getElement(sourceElementID));
			}

			//Link jumper elements to source
			if (connection.getSourceType() == Connection.Type.JUMPERS) {
				String sourceJumperID = rawConnection.getSourceID();
				Jumper sourceJumper = project.getJumper(sourceJumperID);
				
				connection.setSourceElement(sourceJumper.getElement());
			}
			
			//Link target elements
			if (connection.getTargetType() == Connection.Type.ELEMENTS) {
				String targetID = rawConnection.getTargetID();
				connection.setTargetElement(project.getElement(targetID));
			}
			
			//Link jumper elements to targets
			if (connection.getTargetType() == Connection.Type.JUMPERS) {
				String targetJumperID = rawConnection.getTargetID();
				Jumper targetJumper = project.getJumper(targetJumperID);
				
				connection.setTargetElement(targetJumper.getElement());
			}

			
			//System.err.println(connection.getID() + " " + connection.getSourceElement().getID() + " " + (connection.getTargetElement() != null ? connection.getTargetElement().getID() : "No target"));
			
		}
		
		/*
		 * Components
		 */
		
		ArrayList<Component> components = project.getComponents();
		
		for (int i = 0; i < components.size(); i++) {
			Component component = components.get(i);
			RawComponent rawComponent = rawProject.getComponent(component.getID());
			
			//Children
			ArrayList<String> childrenIDs = rawComponent.getChildrenIDs();
			
			for (int j = 0; j < childrenIDs.size(); j++) {
				String childID = childrenIDs.get(j);
				component.getChildren().add(project.getComponent(childID));
			}
			
			//Attributes
			ArrayList<String> attributeIDs = rawComponent.getAttributeIDs();
			
			for (int j = 0; j < attributeIDs.size(); j++) {
				String attributeID = attributeIDs.get(j);
				component.getAttributes().add(project.getAttribute(attributeID));
			}
		}
		
		/*
		 * Attributes
		 */
		
		ArrayList<Attribute> attributes = project.getAttributes();
		
		for (int i = 0; i < attributes.size(); i++) {
			Attribute attribute = attributes.get(i);
			RawAttribute rawAttribute = rawProject.getAttribute(attribute.getID());
			
			//Attribute Values
			AttributeValue attributeValue = attribute.getValue();
			RawAttributeValue rawAttributeValue = rawAttribute.getValue();
			
			//Assign parent 
			switch(attribute.getCType()) {
			case COMPONENTS:
				attribute.setParent(project.getComponent(attribute.getCID()));
				break;
			case ELEMENTS:
				attribute.setParent(project.getElement(attribute.getCID()));
				break;
			default:
				break;
			}
			
			//If the attribute type is a string, then make sure to link its content, in case it has any ComponentMentions.
			if (rawAttributeValue.getValueType() == AttributeValue.Type.STRING) {
				attribute.getValue().getContent().linkComponentMentions(project);
			}
			
			//If the AttributeValue type is a component list, then link all of those references. For string content, that is copied over during the first pass.
			if (rawAttributeValue.getValueType() == AttributeValue.Type.COMPONENT_LIST) {
				String[] data = rawAttributeValue.getData();
				
				for (int j = 0; j < data.length; j++) {

					String[] rawComponentList = rawAttributeValue.getData();
					Component[] componentList = new Component[rawComponentList.length];
					
					for (int k = 0; k < componentList.length; k++) {
						componentList[k] = project.getComponent(rawComponentList[k]);
					}
					
					attributeValue.setComponentList(componentList);
				}
			}
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
	
}
