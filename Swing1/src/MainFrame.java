import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

//Recommends to do this for most Swing projects. Create own custom class based on JFrame (extends JFrame)
// https://www.udemy.com/course/java-swing-complete/learn/lecture/104273#announcements

public class MainFrame extends JFrame {
	
	//create components as private instance variables so can access from multiple methods
	private JTextArea textArea;
	private JButton btn;
	
	
	//Constructor
	public MainFrame () {
		super("Hello World");
		
		//https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
		//set Layout Manager
		
		setLayout(new BorderLayout());
		
		//instantiate the two fields
		textArea = new JTextArea();
		btn = new JButton("Click Me!");
		
		//add the text area to the layout (in the center). Can type text in here
		add(textArea, BorderLayout.CENTER);
		//add the button to the bottom of the layout.
		add(btn, BorderLayout.SOUTH);
		
		
		this.setSize(600, 500);
		//make sure application exits when click on close button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//now need to make the frame visible...
		this.setVisible(true);	
	}
}
