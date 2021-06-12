package com.nokoriware.arcweave.test;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nokoriware.nngine.arcweave.io.ArcweaveJsonImporter;
import com.nokoriware.nngine.arcweave.project.ArcweaveProject;

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
    			ArcweaveProject project = ArcweaveJsonImporter.read(f);
    			basicDialogueProgram(project);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		
        } else {
        	System.out.println("No file selected. Terminating program.");
        }
	}
	
	public static void basicDialogueProgram(ArcweaveProject project) {
		System.out.println("Beginning dialogue test. The selected project is \"" + project.getName() + ".\"");
		Scanner scanner = new Scanner(System.in);

		String response = scanner.nextLine(); // Read user input
		
		System.out.println("End of dialogue. Terminating.");
		scanner.close();
	}

}
