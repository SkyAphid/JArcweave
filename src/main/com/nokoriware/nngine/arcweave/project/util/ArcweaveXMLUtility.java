package com.nokoriware.nngine.arcweave.project.util;

/**
 * This class is for reading various XML tags used in the content of Arcweave Project data.
 */
public class ArcweaveXMLUtility {

	public static String removeParagraphTags(String content) {
		return content.replace("<p>", "").replaceAll("<\\/p>", "");
	}
}
