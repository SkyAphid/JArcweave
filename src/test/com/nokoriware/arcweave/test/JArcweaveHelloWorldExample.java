package com.nokoriware.arcweave.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nokoriware.nngine.arcweave.io.ArcweaveJsonImporter;
import com.nokoriware.nngine.arcweave.io.ArcweaveProjectProcessor;
import com.nokoriware.nngine.arcweave.project.processed.*;
import com.nokoriware.nngine.arcweave.project.raw.*;

public class JArcweaveHelloWorldExample {
	
	public static void main(String[] args) {
		System.out.println("Hello world. Select an Arcweave JSON file to test.");
		
		/*
		 * Set look and feel because I'm OCD
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Select the Arcweave file to test.
		 */
        JFileChooser chooser = new JFileChooser();
        
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arcweave JSON Files", "json");
        chooser.setFileFilter(filter);
        
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	File f = chooser.getSelectedFile();
        	
        	/*
        	 * Run the program.
        	 */
        	
    		try {
    			RawArcweaveProject rawProject = ArcweaveJsonImporter.read(f);
    			ArcweaveProject project = ArcweaveProjectProcessor.process(rawProject);
    			
    			System.out.println("Arcweave Project successfully imported and processed.");

    			basicDialogueProgram(project);
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
        } else {
        	System.out.println("No file selected. Terminating program.");
        	System.exit(1);
        }
	}
	
	public static void basicDialogueProgram(ArcweaveProject project) {
		
		/*
		 * Welcome text
		 */
		
		System.out.println("Beginning dialogue test."
				+ "\n\nThe selected project is \"" + project.getName() + ".\""
				+ "\nThe dialogue will begin at the Starting Element.\n");
		
		/*
		 * Begin dialogue
		 */
		
		Scanner scanner = new Scanner(System.in);
		
		Element currentElement = project.getStartingElement();
		
		while (currentElement != null) {
			//Print dialogue
			System.out.println("\"" + currentElement.getContent() + "\"");
			
			//If the connections contain labels, we'll print them as selectable options.
			if (currentElement.connectionsContainLabels()) {
				
				//Print available responses
				ArrayList<Connection> options = currentElement.getConnectionOutputs();
				
				System.out.println();
				
				for (int i = 0; i < options.size(); i++) {
					Connection connection = options.get(i);
					System.out.println(i + ": " + connection.getLabel());
				}
				
				//Obtain user response
				int response = obtainUserResponse(scanner);
				
				if (response >= 0 && response < options.size()) {
					Connection selected = options.get(response);
					
					System.out.println("\n>" + selected.getLabel() + ".");
					
					//System.out.println(selected.getID() + " " + selected.getSourceElement().getID() + " " + (selected.getTargetElement() != null ? selected.getTargetElement().getID() : "No target"));

					//Proceed to next element
					Element target = selected.getTargetElement();
					
					currentElement = target;
				} else {
					System.err.println("Please input an available response number.\n");
				}

				
			} else {
				//Otherwise if there are no responses available, we'll skip straight to the next element.
				ArrayList<Connection> outputs = currentElement.getConnectionOutputs();
				
				//Warning in case there are multiplied unlabelled connection outputs
				if (outputs.size() > 1) {
					System.err.println("Warning: multiple unlabelled connections present in \"" + currentElement.getTitle() + ".\"");
				}
				
				//Proceed to next element, if available
				if (!outputs.isEmpty()) {
					
					Connection connection = outputs.get(0);
					Element target = connection.getTargetElement();
					currentElement = target;
					
				} else {
					
					//If there is no element, end the dialogue exchange.
					currentElement = null;
				}

			}
		}
		
		/*
		 * Close Program
		 */
		
		System.out.println("\nEnd of dialogue. Terminating program.");
		scanner.close();
		System.exit(0);
	}
	
	private static int obtainUserResponse(Scanner scanner) {
		System.out.println("\nType the corresponding number of the response you want to reply with:");
		String response = scanner.nextLine();
		
		try {
			
			int chosenOption = Integer.parseInt(response);
			return chosenOption;
			
		} catch (NumberFormatException e) {
			
			System.err.println("Invalid number.");
			return -1;
		}
	}

}
