package org.PlagiarismDetector.UI;

import java.awt.*;
import javax.swing.*;

/**
 * A static form to display the about dialog.
 * @author <a href="mailto:umair.marry7@gmail.com">Umair Bhatti</a>
 * 
 * Created: December 1, 2015
 * Modified: Feb 8, 2016
 */

public class aboutForm extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4450915557767046124L;
	private int width = 640;
    private int height = 480;
    // Size and location of dialog
    aboutForm()
    {
        setResizable(false);
        setTitle("About");
        setSize(width, height);
        setLocation(500, 200);
    }
    // Drawing of credits
    @Override
    public void paint(Graphics gc)
    {
        gc.setColor(Color.black);
        gc.setFont(new Font("Font for credits", Font.PLAIN, 16));
        gc.drawString("Plagirsim detection Application prototype for 2 text (doc) files.", 5, 100);
        gc.drawString("Version: 1.0", 5, 120);
        gc.drawString("Build Number: " + serialVersionUID, 5, 140);
        gc.drawString("Copyright (C) Virtual University Pakistan", 5, 160);
        gc.drawString("Email: mcRashid@vu.edu.pk", 5, 180);
        gc.drawString("Web: http://vu.edu.pk", 5, 200);
    }
}