package jonathan.furminger.mazegenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class MazeGenerator extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int rows = 5;
	private int cols = 5;
	private Cell[][] cell = new Cell[rows][cols];

	private TitleLabel titleLabel = new TitleLabel("Maze");
	private JPanel mazePanel = new JPanel();
	
	public static void main(String[] args) {
		String className = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MazeGenerator();
			}
		});
	}
	
	public MazeGenerator() {
		initGUI();
		setTitle("MazeGenerator");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	private void initGUI() {
		add(titleLabel, BorderLayout.PAGE_START);
		
		// center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.BLACK);
		add(centerPanel, BorderLayout.CENTER);
		
		// maze panel
		newMaze();
		centerPanel.add(mazePanel);
		
		// button panel
		
		// listeners
		
	}
	
	private void newMaze() {
		mazePanel.setLayout(new GridLayout(rows, cols));
		
		cell = new Cell[rows][cols];
		
		for(int r = 0 ; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				cell[r][c] = new Cell(r, c);
				mazePanel.add(cell[r][c]);
			}
		}
	}

}
