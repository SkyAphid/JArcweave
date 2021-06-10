package com.nokoriware.arcweave.test;

import java.io.File;
import java.io.FileInputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.nokoriware.nngine.arcweave.project.*;
import com.nokoriware.nngine.arcweave.project.Connection.Type;

public class ArcweaveJSONImporter {

	public static void main(String[] args) {
		System.out.println("Running import test.");

		try {
			read(new File("helloworld.json"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void read(File f) throws Exception {

		/*
		 * Load JSON file and prepare it for reading
		 */

		FileInputStream inputStream = new FileInputStream(f);
		JsonReader jsonReader = Json.createReader(inputStream);

		JsonObject projectObject = jsonReader.readObject();

		/*
		 * Create new Arcweave Project class
		 */

		Project project = new Project();

		/*
		 * Basic project information
		 * 
		 */

		String projectName = projectObject.getString("name");
		project.setName(projectName);
		// System.out.println(projectName);

		String startingElementID = projectObject.getString("startingElement");
		project.setStartingElementID(startingElementID);
		// System.out.println(startingElementID);

		/*
		 * Notes
		 */

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

		/*
		 * Elements
		 */

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

			// linked boards go here, if ever needed
		});

		/*
		 * Jumpers
		 */

		JsonObject jumpers = projectObject.getJsonObject("jumpers");

		jumpers.forEach((jumperID, jumperValue) -> {

			// create jumper
			Jumper jumper = new Jumper(jumperID);

			// get element ID of what it jumps to
			JsonObject jumperObject = jumperValue.asJsonObject();
			String elementID = jumperObject.getString("elementId");

			// set the element ID to the jumper object
			jumper.setElementID(elementID);
		});

		/*
		 * Connections
		 */

		JsonObject connections = projectObject.getJsonObject("connections");

		connections.forEach((connectionID, connectionValue) -> {

			JsonObject connectionObject = connectionValue.asJsonObject();

			// create connection
			Connection connection = new Connection(connectionID);

			// label - try/catch for in case there's no label on the connection
			try {

				String label = connectionObject.getString("label");
				connection.setLabel(label);
				// System.out.println(label);

			} catch (ClassCastException e) {

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
			Type sourceType = Type.checkType(sourceTypeID);
			connection.setSourceType(sourceType);

			// target type
			String targetTypeID = connectionObject.getString("targetType");
			Type targetType = Type.checkType(targetTypeID);
			connection.setTargetType(targetType);

		});


		/*
		 * Components
		 */
		
		

		/*
		 * Attributes
		 */

		/*
		 * Variables - todo
		 */

		/*
		 * Conditions - todo
		 */

		/*
		 * Branches - todo
		 */

		/*
		 * Boards
		 * 
		 * Here, we assemble all of the loaded objects build them into boards, where all the data is hosted cleanly.
		 */

		JsonObject boards = projectObject.getJsonObject("boards");

		boards.forEach((boardID, boardValue) -> {

			Board board = new Board(boardID);

		});
	}
}
