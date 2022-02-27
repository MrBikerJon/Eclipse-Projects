package jonathan.furminger.dotsandboxes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private DotsAndBoxes dotsAndBoxes;
	private int rows = 10;
	private int columns = 10;
	private int width;
	private int height;
	private Box[][] boxes;
	private boolean myTurn = false;
	private Rectangle cursor;
	
	public GamePanel(DotsAndBoxes dotsAndBoxes, int rows, int columns) {
		this.dotsAndBoxes = dotsAndBoxes;
		this.rows = rows;
		this.columns = columns;
		width = (columns + 2) * Box.SIZE;
		height = (rows + 2) * Box.SIZE;
		
		newGame();
		
		// listeners
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				if(myTurn) {
					int x = e.getX();
					int y = e.getY();
					mouseMovedTo(x, y);
				}
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(myTurn) {
					int x = e.getX();
					int y = e.getY();
					clicked(x, y);
				}
			}
		});
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}
	
	private void newGame() {
		boxes = new Box[rows][columns];
		int y = Box.SIZE;
		for(int r = 0; r < rows; r++) {
			int x = Box.SIZE;
			for(int c = 0; c < columns; c++) {
				boxes[r][c] = new Box(x, y);
				x += Box.SIZE;
			}
			y += Box.SIZE;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// boxes
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				boxes[r][c].draw(g);
			}
		}
		
		// cursor
		if(cursor != null) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawRect(cursor.x, cursor.y, cursor.width, cursor.height);
		}
	}
	
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
	
	public void mouseMovedTo(int x, int y) {
		int row = y / Box.SIZE - 1;
		int col = x / Box.SIZE - 1;
		
		if(row >= 0 && row < rows && col >= 0 && col < columns) {
			int side = boxes[row][col].getClosestSide(x, y);
			cursor = boxes[row][col].getLine(side);
		}
		else {
			cursor = null;
		}
		
		repaint();
	}
	
	private boolean drawLine(int row, int col, int side) {
		boolean added = boxes[row][col].addLine(side, myTurn);
		return added;
	}
	
	private void clicked(int x, int y) {
		int row = y / Box.SIZE - 1;
		int col = x / Box.SIZE - 1;
		if(row >= 0 && row < rows && col >= 0 && col < columns) {
			int side = boxes[row][col].getClosestSide(x, y);
			connectDots(row, col, side);
		}
	}
	
	public boolean connectDots(int row, int col, int side) {
		boolean validLine = drawLine(row, col, side);
		
		if(validLine) {
			switch(side) {
			case Box.LEFT :
				if(col > 0) {
					drawLine(row, col - 1, Box.RIGHT);
				}
				break;
			case Box.RIGHT :
				if(col < columns - 1) {
					drawLine(row, col + 1, Box.LEFT);
				}
				break;
			case Box.TOP :
				if(row > 0) {
					drawLine(row - 1, col, Box.BOTTOM);
				}
				break;
			case Box.BOTTOM :
				if(row < rows - 1) {
					drawLine(row + 1, col, Box.TOP);
				}
				break;
			}
		}
		repaint();
		return validLine;
	}
	
}
