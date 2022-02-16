package jonathan.furminger.catandmouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import jonathan.furminger.mycomponents.ScorePanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ScorePanel scorePanel;
	private int width = 640;
	private int height = 400;
	private Maze maze;
	private Mouse mouse;
	private Timer timer;
	
	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		
		maze = new Maze();
		width = maze.getWidth();
		height = maze.getHeight();
		
		initGUI();
		mouse = new Mouse(this, maze);
		
		timer.start();
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		
		// background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, width, height);
		
		// maze
		maze.draw(g);
		
		// mouse
		mouse.draw(g);
		
		
		// extra mice
		
		
		// cat
	}
	
	private void initGUI() {
		setFocusable(true);
		requestFocusInWindow();
		
		// listeners
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int direction = Mouse.DIRECTION_NONE;
				int code = e.getKeyCode();
				switch(code) {
				case KeyEvent.VK_UP :
					direction = Mouse.DIRECTION_UP;
					break;
				case KeyEvent.VK_DOWN :
					direction = Mouse.DIRECTION_DOWN;
					break;
				case KeyEvent.VK_LEFT :
					direction = Mouse.DIRECTION_LEFT;
					break;
				case KeyEvent.VK_RIGHT :
					direction = Mouse.DIRECTION_RIGHT;
					break;
				}
				if(direction != Mouse.DIRECTION_NONE) {
					mouse.turn(direction);
					repaint();
					mouse.setState(mouse.STATE_RUN);
				}
			}
		});
		
		// timer
		timer = new Timer(60, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timedAction();
			}
		});
	}
	
	public void timedAction() {
		mouse.move();
		repaint();
	}

}
