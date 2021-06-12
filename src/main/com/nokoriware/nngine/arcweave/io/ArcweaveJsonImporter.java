package com.nokoriware.nngine.arcweave.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue.ValueType;

import com.nokoriware.nngine.arcweave.project.*;

/**
 * This class will import an Arcweave JSON export file and convert it into an object-based system for use in java-based projects.
 * 
 * <br><br>
 * Official documentation on Arcweave can be found here for more information on what exactly this importer is reading and the general terminology used:
 * <br>• https://arcweave.com/docs/1.0/overview
 * 
 * <br><br>Aspects of an Arcweave JSON export that are not currently loaded by this importer:
 * <br>• Assets
 * <br>• Variables
 * <br>• Conditions
 * <br>• Branches
 * 
 */
public class ArcweaveJsonImporter {

	/**
	 * A utility function that allows you to simply pass in a <code>File</code> containing the location of the Arcweave Project you wish to parse.
	 * 
	 * @throws Exception - any exceptions encountered during parsing will be reported.
	 */
	public static ArcweaveProject read(File f) throws Exception {
		FileInputStream inputStream = new FileInputStream(f);
		return read(inputStream);
	}
	
	/**
	 * Read the given input stream containing the JSON data for the given Arcweave Project. All data will be read and recorded onto an object based system that will allow for easier Java integration.
	 * 
	 * @throws Exception - any exceptions encountered during parsing will be reported.
	 */
	public static ArcweaveProject read(InputStream inputStream) throws Exception {

		/*
		 * Load JSON file and prepare it for reading
		 */

		JsonReader jsonReader = Json.createReader(inputStream);

		JsonObject projectObject = jsonReader.readObject();

		/*
		 * Create new ArcweaveProject class
		 */

		ArcweaveProject project = new ArcweaveProject();

		/*
		 * Begin reading the file and assigning data.
		 */

		//General information on the project
		readGeneralInformation(projectObject, project);
		
		//Cover
		readCover(projectObject, project);
		
		//Boards
		readBoards(projectObject, project);
		
		//Notes
		readNotes(projectObject, project);

		//Elements
		readElements(projectObject, project);

		//Jumpers
		readJumpers(projectObject, project);
		
		//Connections
		readConnections(projectObject, project);

		//Components
		readComponents(projectObject, project);
		
		//Attributes
		readAttributes(projectObject, project);
		
		//Branches
		readAttributes(projectObject, project);
		
		//Variables
		readVariables(projectObject, project);
		
		//Conditions
		readConditions(projectObject, project);

		/*
		 * Return the completed ArcweaveProject.
		 */
		
		return project;
	}
	
	/**
	 * Read the general project information, such as the name and starting element.
	 */
	public static void readGeneralInformation(JsonObject projectObject, ArcweaveProject project) {
		//The name of the project
		String projectName = projectObject.getString("name");
		project.setName(projectName);
		// System.out.println(projectName);

		//Starting element - which dialogue node the dialogue would start at if ran internally on Arcweave
		if (projectObject.get("startingElement").getValueType() != ValueType.NULL) {
			
			String startingElementID = projectObject.getString("startingElement");
			project.setStartingElementID(startingElementID);
			// System.out.println(startingElementID);
		} else {
			
			project.setStartingElementID("");
		}
	}
	
	/**
	 * Read the project cover information. This is the image you can assign to Arcweave projects.
	 */
	public static void readCover(JsonObject projectObject, ArcweaveProject project) {
		if (projectObject.get("cover").getValueType() != ValueType.NULL) {
			
			JsonObject coverObject = projectObject.getJsonObject("cover");
			
			//Create cover
			Cover cover = new Cover();
			
			//Cover filename
			String fileName = coverObject.getString("file");
			cover.setFileName(fileName);
			
			//Cover type
			String coverTypeName = coverObject.getString("type");
			cover.setType(Cover.Type.get(coverTypeName));

			//Add cover to project
			project.setCover(cover);
		} else {
			
			project.setCover(null);
		}
		
	}
	
	/**
	 * Read the project board information. A board is where exchanges of dialogue are located.
	 */
	public static void readBoards(JsonObject projectObject, ArcweaveProject project) {

		JsonObject boards = projectObject.getJsonObject("boards");

		boards.forEach((boardID, boardValue) -> {
			
			JsonObject boardObject = boardValue.asJsonObject();

			//Create board
			Board board = new Board(boardID);
			
			//Board name
			String boardName = boardObject.getString("name");
			board.setName(boardName);
			
			//Board notes
			if (boardObject.containsKey("notes")) {
				
				JsonArray boardNotes = boardObject.getJsonArray("notes");
				
				for (int i = 0; i < boardNotes.size(); i++) {
					
					String boardNoteID = boardNotes.getString(i);
					board.getNoteIDs().add(boardNoteID);
				}
			}
			
			//Board jumpers
			if (boardObject.containsKey("jumpers")) {
				
				JsonArray boardJumpers = boardObject.getJsonArray("jumpers");
				
				for (int i = 0; i < boardJumpers.size(); i++) {
					
					String boardJumperID = boardJumpers.getString(i);
					board.getJumperIDs().add(boardJumperID);
				}
			}
			
			//Board branches
			if (boardObject.containsKey("branches")) {
				
				JsonArray boardBranches = boardObject.getJsonArray("branches");
				
				for (int i = 0; i < boardBranches.size(); i++) {
					
					String boardBranchID = boardBranches.getString(i);
					board.getBranchIDs().add(boardBranchID);
				}
			}
			
			//Board elements
			if (boardObject.containsKey("elements")) {
				
				JsonArray boardElements = boardObject.getJsonArray("elements");
				
				for (int i = 0; i < boardElements.size(); i++) {
					
					String boardElementID = boardElements.getString(i);
					board.getElementIDs().add(boardElementID);
				}
			}
			
			//Board connections
			if (boardObject.containsKey("connections")) {
				
				JsonArray boardConnections = boardObject.getJsonArray("connections");
				
				for (int i = 0; i < boardConnections.size(); i++) {
					
					String boardConnectionID = boardConnections.getString(i);
					board.getConnectionIDs().add(boardConnectionID);
				}
			}
			
			//Add board to project
			project.getBoards().add(board);
		});
	}
	
	/**
	 * Read the project notes. Notes can be placed on boards to help keep things organized while working on a project.
	 */
	public static void readNotes(JsonObject projectObject, ArcweaveProject project) {
		JsonObject notes = projectObject.getJsonObject("notes");

		notes.forEach((noteID, noteValue) -> {

			// create note
			Note note = new Note(noteID);

			// load content
			JsonObject noteObject = noteValue.asJsonObject();

			String content = noteObject.getString("content");
			note.setContent(content);

			// set content
			project.getNotes().add(note);
		});
	}
	
	/*
	 * Read the project elements. Elements are the dialogue nodes that contain your dialogue.
	 */
	public static void readElements(JsonObject projectObject, ArcweaveProject project) {
		JsonObject elements = projectObject.getJsonObject("elements");

		elements.forEach((elementID, elementValue) -> {

			// create element
			Element element = new Element(elementID);

			// load data
			JsonObject elementObject = elementValue.asJsonObject();

			// title & content
			String title = elementObject.getString("title");
			element.setTitle(title);

			String content = elementObject.getString("content");
			element.setContent(content);

			// output (connection) IDs
			JsonArray outputArray = elementObject.getJsonArray("outputs");

			for (int i = 0; i < outputArray.size(); i++) {

				String outputID = outputArray.getJsonString(i).getString();
				element.getConnectionIDs().add(outputID);
			}

			// attached component IDs
			JsonArray componentArray = elementObject.getJsonArray("components");

			for (int i = 0; i < componentArray.size(); i++) {

				String componentID = componentArray.getJsonString(i).getString();
				element.getComponentIDs().add(componentID);
			}

			// TODO linked boards go here, if ever needed
			
			//Add elements to Project
			project.getElements().add(element);
		});
	}
	
	/**
	 * Read the general project jumpers. Jumpers are links that can be placed on boards to allow dialogue to jump to elements on other boards.
	 */
	public static void readJumpers(JsonObject projectObject, ArcweaveProject project) {
		JsonObject jumpers = projectObject.getJsonObject("jumpers");

		jumpers.forEach((jumperID, jumperValue) -> {

			// create jumper
			Jumper jumper = new Jumper(jumperID);

			// get element ID of what it jumps to
			JsonObject jumperObject = jumperValue.asJsonObject();
			String elementID = jumperObject.getString("elementId");

			// set the element ID to the jumper object
			jumper.setElementID(elementID);
			
			//Add jumpers to project
			project.getJumpers().add(jumper);
		});
	}
	
	/**
	 * Read connections. These connect elements together to form the various paths of your dialogue.
	 */
	public static void readConnections(JsonObject projectObject, ArcweaveProject project) {
		JsonObject connections = projectObject.getJsonObject("connections");

		connections.forEach((connectionID, connectionValue) -> {

			JsonObject connectionObject = connectionValue.asJsonObject();

			// create connection
			Connection connection = new Connection(connectionID);

			// label
			if (connectionObject.get("label").getValueType() != ValueType.NULL) {
				String label = connectionObject.getString("label");
				connection.setLabel(label);
				//System.out.println(label);
			} else {
				connection.setLabel("");
			}

			// source ID
			String sourceID = connectionObject.getString("sourceid");
			connection.setSourceID(sourceID);

			// target ID
			String targetID = connectionObject.getString("targetid");
			connection.setTargetID(targetID);

			// source type
			String sourceTypeID = connectionObject.getString("sourceType");
			Connection.Type sourceType = Connection.Type.get(sourceTypeID);
			connection.setSourceType(sourceType);

			// target type
			String targetTypeID = connectionObject.getString("targetType");
			Connection.Type targetType = Connection.Type.get(targetTypeID);
			connection.setTargetType(targetType);

			//Add connections to project
			project.getConnections().add(connection);
		});
	}
	
	/**
	 * Read components. Components are draggable identifiers that contain attributes (information/data).
	 */
	public static void readComponents(JsonObject projectObject, ArcweaveProject project) {
		JsonObject components = projectObject.getJsonObject("components");

		components.forEach((componentID, componentValue) -> {
			
			JsonObject componentObject = componentValue.asJsonObject();
			
			//Create component
			Component component = new Component(componentID);
			
			//Component Name
			String name = componentObject.getString("name");
			component.setName(name);
			//System.out.println(name);
			
			//Check if this component is a root component
			if (componentObject.containsKey("root")) {
				
				boolean isRoot = componentObject.getBoolean("root");
				component.setRoot(isRoot);
				
			} else {
				component.setRoot(false);
			}
			
			//Add children component IDs for nested folders, if applicable
			if (componentObject.containsKey("children")) {
				JsonArray childrenIDs = componentObject.getJsonArray("children");

				for (int i = 0; i < childrenIDs.size(); i++) {

					String childID = childrenIDs.getString(i);
					component.getChildrenIDs().add(childID);
					//System.out.println(childID);
				}
			}
			
			//Add attribute IDs
			if (componentObject.containsKey("attributes")) {
				JsonArray attributeIDs = componentObject.getJsonArray("attributes");
				
				for (int i = 0; i < attributeIDs.size(); i++) {
					
					String attributeID = attributeIDs.getString(i);
					component.getAttributeIDs().add(attributeID);
				}
			}
			
			//Add components to project
			project.getComponents().add(component);
		});
	}
	
	/**
	 * Read attributes. Attributes are the data contained within components.
	 */
	public static void readAttributes(JsonObject projectObject, ArcweaveProject project) {
		JsonObject attributes = projectObject.getJsonObject("attributes");
		
		attributes.forEach((attributeID, attributeValue) -> {
			
			JsonObject attributeObject = attributeValue.asJsonObject();
			
			/*
			 * Attribute
			 */
			
			Attribute attribute = new Attribute(attributeID);
			
			//Attribute name
			if (attributeObject.get("name").getValueType() != ValueType.NULL) {
				String name = attributeObject.getString("name");
				attribute.setName(name);
			}
			
			/*
			 * Attribute Values
			 */
			
			JsonObject valueObject = attributeObject.getJsonObject("value");
			
			AttributeValue value = new AttributeValue();
			
			//Value Type
			String valueTypeName = valueObject.getString("type");
			AttributeValue.Type valueType = AttributeValue.Type.get(valueTypeName);
			value.setValueType(valueType);
			
			//Value data
			if (valueType == AttributeValue.Type.STRING) {
				value.setData(valueObject.getString("data"));
			}
			
			if (valueType == AttributeValue.Type.COMPONENT_LIST) {
				JsonArray valueData = valueObject.getJsonArray("data");
				String[] values = new String[valueData.size()];
				
				for (int i = 0; i < valueData.size(); i++) {
					values[i] = valueData.getString(i);
				}
			}
			
			/*
			 * Add attributes to project
			 */
			
			project.getAttributes().add(attribute);
		});
		
	}
	
	/**
	 * Read branches. TODO
	 */
	public static void readBranches(JsonObject projectObject, ArcweaveProject project) {
		
	}
	
	/**
	 * Read variables. TODO
	 */
	public static void readVariables(JsonObject projectObject, ArcweaveProject project) {
		
	}
	
	/**
	 * Read conditions. TODO
	 */
	public static void readConditions(JsonObject projectObject, ArcweaveProject project) {
		
	}
	
}
