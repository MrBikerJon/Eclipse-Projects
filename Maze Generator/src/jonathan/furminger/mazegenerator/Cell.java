package jonathan.furminger.mazegenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Cell extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int SIZE = 20;
	public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int BOTTOM = 2;
	public static final int LEFT = 3;

	private int row, col = -1;
	private boolean[] wall = {true, true, true, true};
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		
	}
	
	public void paintComponent(Graphics g) {
		// draw the background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE, SIZE);
		g.setColor(Color.BLACK);
		
		// draw the walls
		
		// draw the path
		
		// draw the balls
		
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(SIZE, SIZE);
		return size;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean isWall(int index) {
		return wall[index];
	}
	
}


