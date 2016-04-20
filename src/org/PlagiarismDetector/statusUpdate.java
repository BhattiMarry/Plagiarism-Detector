package org.PlagiarismDetector;

import org.PlagiarismDetector.Properties.projectProperties;

/**
 * An (java) interface to help updating the status bar of application. 
 * @author <a href="mailto:umair.marry7@gmail.com">Umair Bhatti</a>
 * 
 * Created: December 1, 2015
 * Modified: Feb 8, 2016
 */


public interface statusUpdate {
	default void updateStatus(String msg){
		projectProperties.getInstance().statusLabel.setText(msg);
	}
}
