package org.PlagiarismDetector.UI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.PlagiarismDetector.FilesComparator;
import org.PlagiarismDetector.statusUpdate;
import org.PlagiarismDetector.Properties.projectProperties;
import org.apache.log4j.chainsaw.Main;

/**
 * Main application interface (GUI).
 * Has the status bar, menu buttons etc.
 * @author <a href="mailto:umair.marry7@gmail.com">Umair Bhatti</a>
 * 
 * Created: December 1, 2015
 * Modified: Feb 8, 2016
 */

public class GUI extends JFrame implements ActionListener, statusUpdate{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1084334472015888463L;
	private projectProperties propertiesInstance;
	private Container container;
	private JPanel statusLinePanel;
	private Dimension d;
	private int height;
	private int width;
	private JLabel copyRightLabel;
	private JLabel imageLabel;
	private ImageIcon imageIcon;
	private JLabel file1LoadedStatus;
	private JLabel file2LoadedStatus;
	private JLabel resultLabel;
	private JPanel completedStepsStatus;
	private static int numberOfFilesLoaded = 0;
	
    private static aboutForm aboutForm = new aboutForm();
	public GUI(){
		width = 700;
		height = 520;
		copyRightLabel = new JLabel("copyright (C) VU");
		imageIcon = new ImageIcon(Main.class.getResource("/resources/Plagiarism.png"));
		imageLabel = new JLabel(imageIcon);
		completedStepsStatus = new JPanel();
		file1LoadedStatus = new JLabel("");
		file2LoadedStatus = new JLabel("");
		resultLabel = new JLabel("");
		propertiesInstance = projectProperties.getInstance();
		init();
	}

	public void init() {
		container = getContentPane();
		
		container.setLayout(new BorderLayout());
		statusLinePanel = new JPanel();
		statusLinePanel.setLayout(new BorderLayout());

		JMenu menuFile = new JMenu("File");
        JMenuItem menuItemLoadFile = new JMenuItem("Load Files");
        menuItemLoadFile.addActionListener(this);
        menuFile.add(menuItemLoadFile);
        
        JMenuItem menuCompareFiles = new JMenuItem("Compare Files");
        menuCompareFiles.addActionListener(this);
        menuFile.add(menuCompareFiles);
        
        JMenuItem menuItemResetFiles = new JMenuItem("Reset");
        menuItemResetFiles.addActionListener(this);
        menuFile.add(menuItemResetFiles);
        
        JMenuItem menuItemQuit = new JMenuItem("Quit");
        menuItemQuit.addActionListener(this);
        menuFile.add(menuItemQuit);
        
        JMenu menuResult = new JMenu("Results");
        JMenuItem menuItemShowResult = new JMenuItem("Show Result");
        menuItemShowResult.addActionListener(this);
        menuResult.add(menuItemShowResult);
        
        JMenuItem menuItemSaveResult = new JMenuItem("Save Result");
        menuItemSaveResult.addActionListener(this);
        menuResult.add(menuItemSaveResult);
        
        JMenu menuAbout = new JMenu("Help");
        JMenuItem menuItemAbout = new JMenuItem("About");
        menuItemAbout.addActionListener(this);
        menuAbout.add(menuItemAbout);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuResult);
        menuBar.add(menuAbout);
		
		container.add("North", menuBar);
		container.add(statusLinePanel, BorderLayout.SOUTH);
		container.add(imageLabel);
		statusLinePanel.add(copyRightLabel,BorderLayout.LINE_END);
		statusLinePanel.add(propertiesInstance.statusLabel,BorderLayout.LINE_START);
		
		completedStepsStatus.setLayout(new BoxLayout(completedStepsStatus, BoxLayout.PAGE_AXIS));
		container.add(completedStepsStatus, BorderLayout.BEFORE_LINE_BEGINS);
		completedStepsStatus.add(file1LoadedStatus);
		completedStepsStatus.add(file2LoadedStatus);
		completedStepsStatus.add(resultLabel);
		
		d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/4, d.height/6);
		setTitle("Plagiarism Detector");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(Main.class.getResource("/resources/Plagiarism.png")).getImage());
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String msg = evt.getActionCommand();
		if(msg.equals("Load Files")){			
			if(numberOfFilesLoaded < 2){
				numberOfFilesLoaded += 1;
				updateStatus("Loading file " + (numberOfFilesLoaded));
				int returnVal = propertiesInstance.fileChooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					if(numberOfFilesLoaded == 1){
						propertiesInstance.file1 = propertiesInstance.fileChooser.getSelectedFile();
						file1LoadedStatus.setText("File 1 loaded: " + propertiesInstance.file1.getName());
					}else{
						propertiesInstance.file2 = propertiesInstance.fileChooser.getSelectedFile();
						file2LoadedStatus.setText("File 2 loaded: " + propertiesInstance.file2.getName());
					}
		        }else{
		        	JOptionPane.showMessageDialog(this, "File loading Cancelled. Try again.");
		        	numberOfFilesLoaded -= 1;
		        }
				updateStatus("File " + numberOfFilesLoaded + " loaded");
				
			}else{
				JOptionPane.showMessageDialog(this, "Maxinum number of files loaded already.");
			}
			updateStatus("");	
		}else if(msg.equals("Compare Files")){
			if(numberOfFilesLoaded == 2){
				FilesComparator fileComparatorObj = new FilesComparator();
				try {
					propertiesInstance.result = fileComparatorObj.compareLines();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resultLabel.setText("Percentage Result: " + propertiesInstance.result + "%");
			}else{
				JOptionPane.showMessageDialog(this, "Can't Compare. Minimum 2 files required for comparison.");
			}
		}else if(msg.equals("Reset")){
			int action = JOptionPane.showConfirmDialog(null, "Reset Files?", "Reset", JOptionPane.YES_NO_OPTION);
			if(action == 0){
				reset();
			}
		}else if(msg.equals("Quit")){
			int action = JOptionPane.showConfirmDialog(null, "Confirm Exit?", "Exit", JOptionPane.YES_NO_OPTION);
			if(action == 0){
				setVisible(false);
				dispose();
				System.exit(0);
			}
		}else if(msg.equals("Show Result")){
			if(numberOfFilesLoaded == 2){
				JOptionPane.showMessageDialog(this, "Plagiarism Result: " + propertiesInstance.result + "%");
			}else{
				JOptionPane.showMessageDialog(this, "Can't Compare. Minimum 2 files required for comparison.");
			}	
		}else if(msg.equals("Save Result")){
			if(numberOfFilesLoaded == 2){
				int val = propertiesInstance.fileChooser.showSaveDialog(this);
				File saveFile = new File("result.txt");
				if(val == JFileChooser.APPROVE_OPTION){
					saveFile = propertiesInstance.fileChooser.getSelectedFile();
					try{
						PrintWriter pw = new PrintWriter(new FileWriter(saveFile));
						pw.println("File 1");
						pw.println("Name: " + propertiesInstance.file1.getName());
						pw.println("Size (bytes): " + propertiesInstance.file1.length());
						pw.println("Path: " + propertiesInstance.file1.getPath());
						
						pw.println();
						pw.println();
						
						pw.println("File 2");
						pw.println("Name: " + propertiesInstance.file2.getName());
						pw.println("Size (bytes): " + propertiesInstance.file2.length());
						pw.println("Path: " + propertiesInstance.file2.getPath());
						
						pw.println();
						pw.println();
						pw.println("Result: " + propertiesInstance.result);
						pw.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}else{
				JOptionPane.showMessageDialog(this, "Can't Compare. Minimum 2 files required for comparison.");
			}			
		}else if(msg.equals("About")){
			updateStatus("About");
			aboutForm.setVisible(true);
		}
	}
	private void reset(){
		numberOfFilesLoaded = 0;
		propertiesInstance.file1 = null;
		propertiesInstance.file2 = null;
		dispose();
		new GUI();
	}
}
