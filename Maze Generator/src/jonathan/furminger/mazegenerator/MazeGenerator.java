package jonathan.furminger.mazegenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class MazeGenerator extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_MAZE = 0;
	public static final int TYPE_ANTIMAZE = 1;
	private int type = TYPE_ANTIMAZE;
	
	private int rows = 30;
	private int cols = 20;
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
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		add(buttonPanel, BorderLayout.PAGE_END);
		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.setFocusable(false);
		newMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMaze();
			}
		});
		buttonPanel.add(newMazeButton);
		JButton optionsButton = new JButton("Options");
		optionsButton.setFocusable(false);
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeOptions();
			}
		});
		buttonPanel.add(optionsButton);
		
		// listeners   
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				moveBall(keyCode);
			}
		});
		
	}
	
	private void newMaze() {
		if(type == TYPE_MAZE) {
			titleLabel.setText("Maze");
		}
		else {
			titleLabel.setText("Anti-Maze");
		}
		mazePanel.removeAll();
		mazePanel.setLayout(new GridLayout(rows, cols));
		
		cell = new Cell[rows][cols];
		
		for(int r = 0 ; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				cell[r][c] = new Cell(r, c, type);
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
		
		mazePanel.revalidate();
		pack();
		
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
	
	private void moveTo(int nextRow, int nextCol, int firstDirection, int secondDirection) {
		cell[row][col].setCurrent(false);
		cell[row][col].addPath(firstDirection);
		row = nextRow;
		col = nextCol;
		cell[row][col].setCurrent(true);
		cell[row][col].addPath(secondDirection);
	}
	
	private void moveBall(int direction) {
		switch(direction) {
		case KeyEvent.VK_UP :
			//move up if this cell does not have a top wall
			if(!cell[row][col].isWall(Cell.TOP)) {
				moveTo(row-1, col, Cell.TOP, Cell.BOTTOM);
				// move up more if this is a long column
				while(!cell[row][col].isWall(Cell.TOP) 
						&& cell[row][col].isWall(Cell.LEFT)
						&& cell[row][col].isWall(Cell.RIGHT)) {
					moveTo(row-1, col, Cell.TOP, Cell.BOTTOM);
				}
			}
			break;
		case KeyEvent.VK_DOWN :
			// move down if this cell does not have a bottom wall
			if(!cell[row][col].isWall(Cell.BOTTOM)) {
				moveTo(row+1, col, Cell.BOTTOM, Cell.TOP);
				// move down more if this is a long column
				while(!cell[row][col].isWall(Cell.BOTTOM) 
						&& cell[row][col].isWall(Cell.LEFT) 
						&& cell[row][col].isWall(Cell.RIGHT)) {
					moveTo(row+1, col, Cell.BOTTOM, Cell.TOP);
				}
			}
			break;
		case KeyEvent.VK_LEFT :
			// move left if this cell does not have a left wall
			if(!cell[row][col].isWall(Cell.LEFT)) {
				moveTo(row, col-1, Cell.LEFT, Cell.RIGHT);
				// move left more if this is a long row
				while(!cell[row][col].isWall(Cell.LEFT) 
						&& cell[row][col].isWall(Cell.TOP) 
						&& cell[row][col].isWall(Cell.BOTTOM)) {
					moveTo(row, col-1, Cell.LEFT, Cell.RIGHT);
				}
			}
			break;
		case KeyEvent.VK_RIGHT :
			// move right if this cell does not have a right wall
			if(!cell[row][col].isWall(Cell.RIGHT)) {
				moveTo(row, col+1, Cell.RIGHT, Cell.LEFT);
				// move right more if this is a long row
				while(!cell[row][col].isWall(Cell.RIGHT)
						&& cell[row][col].isWall(Cell.BOTTOM) 
						&& cell[row][col].isWall(Cell.TOP)) {
					moveTo(row, col+1, Cell.RIGHT, Cell.LEFT);
				}
			}
			break;
		}
		// puzzle solved?
		if(row == endRow && col == endCol) {
			String message = "Congratulations, you solved the maze!";
			JOptionPane.showMessageDialog(null, message);
			
		}
	}
	
	private void changeOptions() {
		OptionsDialog dialog = new OptionsDialog(rows, cols, type);
		dialog.setModal(true);
		dialog.setResizable(false);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		if(!dialog.isCanceled()) {
			rows = dialog.getRows();
			cols = dialog.getColumns();
			type = dialog.getMazeType();
			newMaze();
		}
	}

}
