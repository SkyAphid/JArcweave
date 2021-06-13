package com.nokoriware.nngine.arcweave.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue.ValueType;

import com.nokoriware.nngine.arcweave.project.processed.ArcweaveProject;
import com.nokoriware.nngine.arcweave.project.processed.AttributeValue;
import com.nokoriware.nngine.arcweave.project.processed.Connection;
import com.nokoriware.nngine.arcweave.project.processed.Cover;
import com.nokoriware.nngine.arcweave.project.raw.*;

/**
 * This class will import an Arcweave JSON export file as an unprocessed set of object-based classes. 
 * This data can then be processed into user-friendly data with one more step afterwards. Both options are available to accomodate multiple usecases.
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
@SuppressWarnings("unused")
public class ArcweaveJsonImporter {

	/**
	 * A utility function that allows you to simply pass in a <code>File</code> containing the location of the Arcweave Project you wish to parse.
	 * 
	 * @throws Exception - any exceptions encountered during parsing will be reported.
	 */
	public static RawArcweaveProject read(File f) throws Exception {
		FileInputStream inputStream = new FileInputStream(f);
		return read(inputStream);
	}
	
	/**
	 * Read the given input stream containing the JSON data for the given Arcweave Project. All data will be read and recorded onto an object based system that will allow for easier Java integration.
	 * 
	 * @throws Exception - any exceptions encountered during parsing will be reported.
	 */
	public static RawArcweaveProject read(InputStream inputStream) throws Exception {

		/*
		 * Load JSON file and prepare it for reading
		 */

		JsonReader jsonReader = Json.createReader(inputStream);

		JsonObject projectObject = jsonReader.readObject();

		/*
		 * Create new ArcweaveProject class
		 */

		RawArcweaveProject project = new RawArcweaveProject();

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
	private static void readGeneralInformation(JsonObject projectObject, RawArcweaveProject project) {
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
	 * The cover does not need to be processed due to its simplicity.
	 */
	private static void readCover(JsonObject projectObject, RawArcweaveProject project) {
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
	private static void readBoards(JsonObject projectObject, RawArcweaveProject project) {

		JsonObject boards = projectObject.getJsonObject("boards");

		boards.forEach((boardID, boardValue) -> {
			
			JsonObject boardObject = boardValue.asJsonObject();

			//Create board
			RawBoard board = new RawBoard(boardID);
			
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
	private static void readNotes(JsonObject projectObject, RawArcweaveProject project) {
		JsonObject notes = projectObject.getJsonObject("notes");

		notes.forEach((noteID, noteValue) -> {

			// create note
			RawNote note = new RawNote(noteID);

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
	private static void readElements(JsonObject projectObject, RawArcweaveProject project) {
		JsonObject elements = projectObject.getJsonObject("elements");

		elements.forEach((elementID, elementValue) -> {

			// create element
			RawElement element = new RawElement(elementID);

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
				element.getConnectionOutputIDs().add(outputID);
			}

			// attached component IDs
			JsonArray componentArray = elementObject.getJsonArray("components");

			for (int i = 0; i < componentArray.size(); i++) {

				String componentID = componentArray.getJsonString(i).getString();
				element.getComponentIDs().add(componentID);
			}

			//linked board
			if (elementObject.get("linkedBoard").getValueType() != ValueType.NULL) {
				
				String linkedBoardID = elementObject.getString("linkedBoard");
				element.setLinkedBoardID(linkedBoardID);
				
			} else {
				
				element.setLinkedBoardID("");
			}
			
			//Add elements to Project
			project.getElements().add(element);
		});
	}
	
	/**
	 * Read the general project jumpers. Jumpers are links that can be placed on boards to allow dialogue to jump to elements on other boards.
	 */
	private static void readJumpers(JsonObject projectObject, RawArcweaveProject project) {
		JsonObject jumpers = projectObject.getJsonObject("jumpers");

		jumpers.forEach((jumperID, jumperValue) -> {

			// create jumper
			RawJumper jumper = new RawJumper(jumperID);

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
	private static void readConnections(JsonObject projectObject, RawArcweaveProject project) {
		JsonObject connections = projectObject.getJsonObject("connections");

		connections.forEach((connectionID, connectionValue) -> {

			JsonObject connectionObject = connectionValue.asJsonObject();

			// create connection
			RawConnection connection = new RawConnection(connectionID);

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
	private static void readComponents(JsonObject projectObject, RawArcweaveProject project) {
		JsonObject components = projectObject.getJsonObject("components");

		components.forEach((componentID, componentValue) -> {
			
			JsonObject componentObject = componentValue.asJsonObject();
			
			//Create component
			RawComponent component = new RawComponent(componentID);
			
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
	private static void readAttributes(JsonObject projectObject, RawArcweaveProject project) {
		JsonObject attributes = projectObject.getJsonObject("attributes");
		
		attributes.forEach((attributeID, attributeValue) -> {
			
			JsonObject attributeObject = attributeValue.asJsonObject();
			
			/*
			 * Attribute
			 */
			
			RawAttribute attribute = new RawAttribute(attributeID);
			
			//Attribute name
			if (attributeObject.get("name").getValueType() != ValueType.NULL) {
				String name = attributeObject.getString("name");
				attribute.setName(name);
			} else {
				attribute.setName("");
			}
			
			/*
			 * Attribute Values
			 */
			
			JsonObject valueObject = attributeObject.getJsonObject("value");
			
			RawAttributeValue value = new RawAttributeValue();
			
			//Value Type
			String valueTypeName = valueObject.getString("type");
			
			AttributeValue.Type valueType = AttributeValue.Type.get(valueTypeName);
			value.setValueType(valueType);
			
			//Value data
			if (valueObject.get("data").getValueType() != ValueType.NULL) {

				//String content
				if (valueType == AttributeValue.Type.STRING) {
					value.setData(valueObject.getString("data"));
				}

				//Component list IDs
				if (valueType == AttributeValue.Type.COMPONENT_LIST) {
					JsonArray valueData = valueObject.getJsonArray("data");
					String[] values = new String[valueData.size()];

					for (int i = 0; i < valueData.size(); i++) {
						values[i] = valueData.getString(i);
					}
					
					value.setData(values);
				}

			} else {
				//If value data content is null, set it to an empty string
				value.setData(new String[] { "" });
			}
			
			//Set the attributes value to our newly created one
			attribute.setValue(value);

			//System.out.println("1   " + attribute.getID() + "    " + Arrays.toString(attribute.getValue().getData()));
			
			/*
			 * Add attributes to project
			 */
			
			project.getAttributes().add(attribute);
		});
		
	}
	
	/**
	 * Read branches. TODO
	 */

	private static void readBranches(JsonObject projectObject, RawArcweaveProject project) {
		
	}
	
	/**
	 * Read variables. TODO
	 */
	private static void readVariables(JsonObject projectObject, RawArcweaveProject project) {
		
	}
	
	/**
	 * Read conditions. TODO
	 */
	private static void readConditions(JsonObject projectObject, RawArcweaveProject project) {
		
	}
	
}
