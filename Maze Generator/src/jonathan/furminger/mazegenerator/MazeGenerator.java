package jonathan.furminger.mazegenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	
	private int row = 0;
	private int col = 0;
	private int endRow = row-1;
	private int endCol = col-1;

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
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				moveBall(keyCode);
			}
		});
		
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
		
		row = 0;
		col = 0;
		endRow = rows-1;
		endCol = cols-1;
		cell[row][col].setCurrent(true);
		cell[endRow][endCol].setEnd(true);
		
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
	
	private void moveTo(int nextRow, int nextCol) {
		cell[row][col].setCurrent(false);
		row = nextRow;
		col = nextCol;
		cell[row][col].setCurrent(true);
	}
	
	private void moveBall(int direction) {
		switch(direction) {
		case KeyEvent.VK_UP :
			//move up if this cell does not have a top wall
			if(!cell[row][col].isWall(Cell.TOP)) {
				moveTo(row-1, col);
			}
			break;
		case KeyEvent.VK_DOWN :
			// move down if this cell does not have a bottom wall
			if(!cell[row][col].isWall(Cell.BOTTOM)) {
				moveTo(row+1, col);
			}
			break;
		case KeyEvent.VK_LEFT :
			// move left if this cell does not have a left wall
			if(!cell[row][col].isWall(Cell.LEFT)) {
				moveTo(row, col-1);
			}
			break;
		case KeyEvent.VK_RIGHT :
			// move right if this cell does not have a right wall
			if(!cell[row][col].isWall(Cell.RIGHT)) {
				moveTo(row, col+1);
			}
			break;
		}
	}

}