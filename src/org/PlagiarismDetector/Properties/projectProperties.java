package org.PlagiarismDetector.Properties;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.PlagiarismDetector.UI.GUI;

/**
 * A singleton class to have the properties used all over the application.
 * @author <a href="mailto:umair.marry7@gmail.com">Umair Bhatti</a>
 * 
 * Created: December 1, 2015
 * Modified: Feb 8, 2016
 */

public class projectProperties {
	private static projectProperties Instance = new projectProperties();
	
	public File file1 = null;
	public File file2 = null;
	public JLabel statusLabel;
	public double result = 0.0;
	public JFileChooser fileChooser;
	public GUI mainObj;
	private projectProperties() {
		statusLabel = new JLabel("Ready");
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Doc Files", "docx"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File", "txt"));
	}
	public static projectProperties getInstance(){
		return Instance;
	}
}
