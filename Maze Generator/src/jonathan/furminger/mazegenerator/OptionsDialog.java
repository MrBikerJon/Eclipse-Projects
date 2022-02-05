package jonathan.furminger.mazegenerator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private boolean canceled = true;
	
	public OptionsDialog(int rows, int cols, int type) {
		this.rows = rows;
		this.cols = cols;
		this.type = type;
		
		setTitle("Maze Generator Options");
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
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
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(mazeButton);
		buttonGroup.add(antiMazeButton);
		
		if(type == MazeGenerator.TYPE_MAZE) {
			mazeButton.setSelected(true);
		}
		else {
			antiMazeButton.setSelected(true);
		}
		
		
		// button panel
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.PAGE_END);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		buttonPanel.add(okButton);
		
	}
	
	private void close() {
		try {
		String rowsString = rowsField.getText();
		String colsString = colsField.getText();
		int newRows = Integer.parseInt(rowsString);
		int newCols = Integer.parseInt(colsString);
		if(newRows > 1 && newCols > 1) {
			rows = newRows;
			cols = newCols;
			if(mazeButton.isSelected()) {
				type = MazeGenerator.TYPE_MAZE;
			}
			else {
				type = MazeGenerator.TYPE_ANTIMAZE;
			}
			setVisible(false);
		}
		else {
			String message = "There must be more than one row and more than one column.";
			JOptionPane.showMessageDialog(this, message);
			}
		}
		catch (NumberFormatException e) {
			String message = "Rows and Columns must be numbers";
			JOptionPane.showMessageDialog(this, message);
		}
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return cols;
	}
	
	public int getMazeType() {
		return type;
	}
	
	public boolean isCanceled() {
		return canceled;
	}
	
}
