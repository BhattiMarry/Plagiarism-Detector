package org.PlagiarismDetector;

/**
 * File reader and text builder from file module. 
 * @author <a href="mailto:umair.marry7@gmail.com">Umair Bhatti</a>
 * 
 * Created: December 1, 2015
 * Modified: Feb 8, 2016
 */

import java.io.File;
import java.io.FileInputStream;

import org.PlagiarismDetector.Properties.projectProperties;
import org.apache.poi.hwpf.HWPFDocument;

public class FileReader {
	projectProperties obj = projectProperties.getInstance();
	public String loadFile(File file){
		String st = "";
		try{
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			HWPFDocument docc = new HWPFDocument(fis);
			StringBuilder strr = docc.getText();
			st = strr.toString();

		}catch(Exception e){
			e.printStackTrace();
		}
		return st;
	}
}