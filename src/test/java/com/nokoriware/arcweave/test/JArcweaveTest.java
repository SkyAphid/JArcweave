package com.nokoriware.arcweave.test;

import java.io.File;

import javax.swing.UIManager;

import com.nokoriware.jarcweave.io.ArcweaveJsonImporter;
import com.nokoriware.jarcweave.io.ArcweaveProjectProcessor;
import com.nokoriware.jarcweave.project.processed.ArcweaveProject;
import com.nokoriware.jarcweave.project.processed.Attribute;
import com.nokoriware.jarcweave.project.processed.Component;
import com.nokoriware.jarcweave.project.processed.Content;
import com.nokoriware.jarcweave.project.processed.Element;
import com.nokoriware.jarcweave.project.processed.Content.ComponentMention;
import com.nokoriware.jarcweave.project.raw.RawArcweaveProject;

public class JArcweaveTest {
	public static void main(String[] args) {

		/*
		 * Set look and feel because I'm OCD
		 */

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		File f = new File("helloworld.json");

		/*
		 * Run the program.
		 */

		try {
			RawArcweaveProject rawProject = ArcweaveJsonImporter.read(f);

			/*RawElement element = rawProject.getElement("f385c762-315b-44ca-9560-edc4763eb8c5");

			Document doc = Jsoup.parse(element.getTitle());
			System.err.println(doc.text());

			System.err.println(doc.getAllElements().size());
			for (int i = 0; i < doc.getAllElements().size(); i++) {
				Element e = doc.getAllElements().get(i);
				System.err.println("Doc " + i + ": " + e.text() + " -> " + e.attr("data-id").toString());
			}*/
			
			ArcweaveProject project = ArcweaveProjectProcessor.process(rawProject);
			
			Element element = project.getStartingElement();
			Content title = element.getTitle();
			
			ComponentMention[] mentions = title.getAllComponentMentions();
			for (ComponentMention mention : mentions) {

				Component c = mention.getLinkedComponent();
				
				System.out.println(mention.getDataLabel() + " Component Attributes:");
				
				int i = 0;
				for (Attribute attribute : c.getAttributes()) {
					System.out.println(i + " " + attribute.getName());
					i++;
				}
			
				System.out.println();
			}
			
			System.out.println("Load success.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
