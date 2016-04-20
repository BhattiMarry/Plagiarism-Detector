package org.PlagiarismDetector;


import java.io.*;
import org.PlagiarismDetector.Properties.projectProperties;

/**
 * Files comparator class. Compares the file at different levels.
 * Line by Line
 * Word by Word
 * Character by Character.
 * @author <a href="mailto:umair.marry7@gmail.com">Umair Bhatti</a>
 * 
 * Created: December 1, 2015
 * Modified: Feb 8, 2016
 */

public class FilesComparator{
	private static String file1Text = new String("");
	private static String file2Text = new String("");
	private static projectProperties instance = projectProperties.getInstance();
	private static FileReader readerObj = new FileReader();
	public FilesComparator() {
		file1Text = readerObj.loadFile(instance.file1);
		file1Text = buildText(file1Text);
		
		file2Text = readerObj.loadFile(instance.file2);
		file2Text = buildText(file2Text);
	}
	public double compareLines() throws IOException{
		double result = 0.0;
		String[] file1Tokens = file1Text.split("\\.");
		String[] file2Tokens = file2Text.split("\\.");
		int i = 0, matched = 0;
		while(i < file1Tokens.length && i < file2Tokens.length){
			if(file1Tokens[i].equals(file2Tokens[i])){
				matched += 1;
			}else if(compareWords(file1Tokens[i], file2Tokens[i])){
				matched += 1;
			}else if(searchString(file1Tokens[i], file2Tokens)){
				matched += 1;
			}else if(searchString(file2Tokens[i], file1Tokens)){
				matched += 1;
			}
			i += 1;
		}
		result = ((double)matched / i) * 100;
		return result;
	}
	private static String buildText(String str){
		String str1 = "";
		int i = 0;
		while(i < str.length()){
			if(str.charAt(i) == 13){
				str1 += "\n";
			}else{
				str1 += str.charAt(i);
			}
			i += 1;
		}
		return str1;
	}
	
	public static boolean compareWords(String line1, String line2){
		int i = 0, matched = 0, notMatched = 0;
		String[] line1Tokens = line1.split(" ");
		String[] line2Tokens = line2.split(" ");
		while(i < line1Tokens.length && i > line2Tokens.length){
			if(line1Tokens[i].equals(line2Tokens[i])){
				matched += 1;
			}else{
				notMatched += 1;
			}
			i += 1;
		}
		if(matched > (notMatched*2)){
			return true;
		}else{
			return false;
		}
	}
	public static boolean searchString(String line, String[] lines){
		for(String str: lines){
			if(str.equals(line)){
				return true;
			}
		}
		return false;
	}
}
