package com.nokoriware.arcweave.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nokoriware.nngine.arcweave.io.ArcweaveJsonImporter;
import com.nokoriware.nngine.arcweave.io.ArcweaveProjectProcessor;
import com.nokoriware.nngine.arcweave.project.*;
import com.nokoriware.nngine.arcweave.project.processed.ArcweaveProject;
import com.nokoriware.nngine.arcweave.project.raw.RawArcweaveProject;
import com.nokoriware.nngine.arcweave.project.raw.RawElement;

public class JArcweaveHelloWorldExample {
	public static void main(String[] args) {
		System.out.println("Running import test.");
		
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

    			basicDialogueProgram(project);
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
        } else {
        	System.out.println("No file selected. Terminating program.");
        }
	}
	
	public static void basicDialogueProgram(ArcweaveProject project) {
		
		/*
		 * Welcome text
		 */
		
		System.out.println("Beginning dialogue test."
				+ "\n\nThe selected project is \"" + project.getName() + ".\""
				+ "\nThe dialogue will begin at the Starting Element.\n\n");
		
		/*
		 * Begin dialogue
		 */
		
		Scanner scanner = new Scanner(System.in);
		
		/*RawElement nextElement = project.getElement(project.getStartingElementID());
		
		while (nextElement != null) {
			System.out.println(nextElement.getContent());
			
			if (nextElement.getConnectionIDs().isEmpty()) {
				
				
			}
			
			String response = scanner.nextLine(); // Read user input
			
		}*/
		
		/*
		 * Close Program
		 */
		
		System.out.println("End of dialogue. Terminating.");
		scanner.close();
	}

}
