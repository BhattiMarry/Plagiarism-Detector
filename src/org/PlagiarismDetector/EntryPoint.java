package org.PlagiarismDetector;
import org.PlagiarismDetector.Properties.projectProperties;
import org.PlagiarismDetector.UI.GUI;

/**
 * Entry point file to lauch the application. Instantiates the objects and Main application interface.
 * @author <a href="mailto:umair.marry7@gmail.com">Umair Bhatti</a>
 * 
 * Created: December 1, 2015
 * Modified: Feb 8, 2016
 */

public class EntryPoint {
	private static projectProperties obj;
	public static void main(String[] argv){
		try{
			obj = projectProperties.getInstance();
			obj.mainObj = new GUI();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
