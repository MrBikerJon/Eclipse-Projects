package jonathan.furminger.mazegenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

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
		generateMaze();
	}
	
	private boolean isAvailable(int r, int c) {
		boolean available = false;
		if(r >= 0 && r < rows && c >= 0 && c < cols && cell[r][c].hasAllWalls()) {
			available = true;
		}
		return available;
	}
	
	private void generateMaze() {
		
		ArrayList<Cell> tryLaterCell = new ArrayList<Cell>();
		int totalCells = rows * cols;
		int visitedCells = 1;
		
		// start at a random cell
		Random rand = new Random();
		int r = rand.nextInt(rows);
		int c = rand.nextInt(cols);
		
		// while not all cells have yet been visited
		
		while(visitedCells < totalCells) {
		
		// find all neighbors with all walls intact
		
			ArrayList<Cell> neighbors = new ArrayList<Cell>();
			if(isAvailable(r-1, c)) {
				neighbors.add(cell[r-1][c]);
			}
			if(isAvailable(r+1, c)) {
				neighbors.add(cell[r+1][c]);
			}
			if(isAvailable(r, c-1)) {
				neighbors.add(cell[r][c-1]);
			}
			if(isAvailable(r, c+1)) {
				neighbors.add(cell[r][c+1]);
			}
			
			
		// if one or more was found
			if(neighbors.size() > 0) {
	
				// if more than one was found, add this
				// cell to the list to try again
				if(neighbors.size() > 1) {
					tryLaterCell.add(cell[r][c]);
				}
				
				// pick a neighbor and remove the wall
		
				int pick = rand.nextInt(neighbors.size());
				Cell neighbor = neighbors.get(pick);
				cell[r][c].openTo(neighbor);
				
				// go to the neighbor and increment
				// the number visited
				r = neighbor.getRow();
				c = neighbor.getCol();
				visitedCells++;
				
			}
			else {
				// if none was found, go to one of the
				// cells that was saved to try later
				Cell nextCell = tryLaterCell.remove(0);
				r = nextCell.getRow();
				c = nextCell.getCol();
			}
		}
	}

}
