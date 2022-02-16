package jonathan.furminger.catandmouse;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Maze {

	public static final int CELL_SIZE = 30;
	
	private static final String FILE_NAME = "/maze.txt";
	private static final int HALF_CELL_SIZE = CELL_SIZE / 2;
	private static final int QUARTER_CELL_SIZE = CELL_SIZE / 4;
	
	private static final int TYPE_PATH = 0;
	private static final int TYPE_WALL = 1;
	private static final int TYPE_CHEESE = 2;
	
	private int rows;
	private int columns;
	private ArrayList<String> mazeData = new ArrayList<String>();
	private int[][] cell;
	private int cheeseCount = 0;
	private int mouseRow;
	private int mouseCol;
	private int catRow;
	private int catCol;
	private ArrayList<Integer> extraMiceX = new ArrayList<Integer>();
	private ArrayList<Integer> extraMiceY = new ArrayList<Integer>();
	
	
	public Maze() {
		readMazeData();
	}
	
	private void readMazeData() {
		try {
			InputStream input = getClass().getResourceAsStream(FILE_NAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			String line = in.readLine();
			while(line != null) {
				mazeData.add(line);
				line = in.readLine();
			}
			in.close();
			
			rows = mazeData.size();
			line = mazeData.get(0);
			columns = line.length();
			cell = new int[rows][columns];
			
			for(int row = 0; row < rows; row++) {
				line = mazeData.get(row);
				for(int col = 0; col < columns; col++) {
					char c = line.charAt(col);
					switch(c) {
					case 'X' :
						cell[row][col] = TYPE_WALL;
						break;
					case ' ' :
						cell[row][col] = TYPE_PATH;
						break;
					case '.' :
						cell[row][col] = TYPE_CHEESE;
						cheeseCount++;
						break;
					case 'M' :
						cell[row][col] = TYPE_PATH;
						mouseRow = row;
						mouseCol = col;
						break;
					case 'C' :
						cell[row][col] = TYPE_CHEESE;
						catRow = row;
						catCol = col;
						cheeseCount++;
						break;
					case 'm' :
						cell[row][col] = TYPE_WALL;
						int x = col * CELL_SIZE;
						int y = row * CELL_SIZE;
						extraMiceX.add(x);
						extraMiceY.add(y);
						break;
					}
				}
			}
			
		}
		catch (NullPointerException e) {
			String message = "The file " + FILE_NAME + " could not be found.";
			JOptionPane.showMessageDialog(null, message);
			System.exit(1);
		}
		catch(IOException e) {
			String message = "The file " + FILE_NAME + " could not be opened.";
			JOptionPane.showMessageDialog(null, message);
			System.exit(2);
		}
	}
	
	public int getWidth() {
		return columns * CELL_SIZE;
	}
	
	public int getHeight() {
		return rows * CELL_SIZE;
	}
	
	public void draw(Graphics g) {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				if(cell[row][col] != TYPE_WALL) {
					int x = col * CELL_SIZE;
					int y = row * CELL_SIZE;
					g.setColor(Color.WHITE);
					g.fillRect(x,  y, CELL_SIZE, CELL_SIZE);
					if(cell[row][col] == TYPE_CHEESE) {
						g.setColor(Color.YELLOW);
						g.fillOval(x + QUARTER_CELL_SIZE, y + QUARTER_CELL_SIZE, HALF_CELL_SIZE, HALF_CELL_SIZE);
					}
				}
			}
		}
	}
	
}
