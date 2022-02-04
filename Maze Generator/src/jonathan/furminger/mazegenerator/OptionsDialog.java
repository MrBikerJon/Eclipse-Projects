package jonathan.furminger.mazegenerator;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class OptionsDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int rows, cols, type = 0;
	
	private JTextField rowsField = new JTextField(3);
	private JTextField colsField = new JTextField(3);
	private JRadioButton mazeButton = new JRadioButton("Maze");
	private JRadioButton antiMazeButton = new JRadioButton("Anti-Maze");
	
	
	public OptionsDialog(int rows, int cols, int type) {
		this.rows = rows;
		this.cols = cols;
		this.type = type;
		
		setTitle("Maze Generator Options");
		
		// main panel
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		JLabel rowsLabel = new JLabel("Rows:");
		mainPanel.add(rowsLabel);
		rowsField.setText("" + rows);
		mainPanel.add(rowsField);
		JLabel colsLabel = new JLabel("Columns:");
		mainPanel.add(colsLabel);
		colsField.setText("" + cols);
		mainPanel.add(colsField);
		
		JLabel typeLabel = new JLabel("Maze Type:");
		mainPanel.add(typeLabel);
		mainPanel.add(mazeButton);
		mainPanel.add(antiMazeButton);
		
		
		// button panel
		
	}
}
