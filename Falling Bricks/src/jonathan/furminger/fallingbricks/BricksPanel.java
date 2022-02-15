package jonathan.furminger.fallingbricks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class BricksPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int ROWS = 15;
	private static final int COLS = 10;
	private static final int WIDTH = COLS * Brick.TILE_SIZE;
	private static final int HEIGHT = ROWS * Brick.TILE_SIZE;
	
	private SBrick brick;
	
	public BricksPanel() {
		initGUI();
		start();
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		//background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// fallen bricks
		
		
		// falling brick
		if(brick != null) {
			brick.draw(g);
		}
	}
	
	public void start() {
		pickABrick();
	}
	
	private void pickABrick() {
		int row = 0;
		int col = COLS / 2;
		brick = new SBrick(row, col);
	}
	
	private void initGUI() {
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch(code) {
				case KeyEvent.VK_LEFT :
					moveLeft();
					break;
				case KeyEvent.VK_RIGHT :
					moveRight();
					break;
				}
			}
		});
	}
	
	private void moveLeft() {
		brick.moveLeft();
		repaint();
	}
	
	private void moveRight() {
		brick.moveRight();
		repaint();
	}

}
