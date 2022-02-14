package jonathan.furminger.babybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import jonathan.furminger.mycommonmethods.FileIO;

public class FlightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int SEPARATION = 40;
	private static final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 30);
	private static final String THUD_SOUND = "/thud.wav";
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	private BabyBird babyBird;
	private Bird bird = new Bird(HEIGHT);
	private Timer timer;
	private ArrayList<Wall> walls = new ArrayList<>();
	private int count = 0;
	private FontMetrics fm;
	
	public FlightPanel(BabyBird babyBird) {
		this.babyBird = babyBird;
		setFont(BIG_FONT);
		fm = getFontMetrics(BIG_FONT);
		
		setFocusable(true);
		requestFocusInWindow();
		
		// listeners
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char key = e.getKeyChar();
				if(key == ' ') {
					bird.startFlapping();
				}
			}
		});
		
		// timer
		timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timedAction();
			}
		});
		
		Wall wall = new Wall(fm);
		walls.add(wall);
		timer.start();
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		// background
		g.setColor(Color.BLUE);
		g.fillRect(0,  0,  WIDTH, HEIGHT);
		
		// bird
		bird.draw(g);
		
		// walls
		for(int i = 0; i < walls.size(); i++) {
			Wall wall = walls.get(i);
			wall.draw(g);
		}
		
	}
	
	private void timedAction() {
		// move bird
		int changeY = bird.getChangeY();
		bird.move();
		
		int paintX = bird.getX();
		int paintY = bird.getY();
		if(changeY > 0) {
			paintY -= changeY;
		}
		int paintWidth = bird.getWidth();
		int paintHeight = bird.getHeight() + Math.abs(changeY);
		repaint(paintX, paintY, paintWidth, paintHeight);
		
		// move walls
		for(int i = 0; i < walls.size(); i++) {
			Wall wall = walls.get(i);
			wall.move();
			paintX = wall.getX();
			paintY = wall.getY();
			paintWidth = wall.getWidth() - wall.getChangeX();
			paintHeight = HEIGHT;
			repaint(paintX, paintY, paintWidth, paintHeight);
			
			if(wall.isPastWindowEdge()) {
				walls.remove(i);
				int points = wall.getPoints();
				babyBird.addToScore(points);
			}
		}
		
		// check for collision
		Wall firstWall = walls.get(0);
		Rectangle birdBounds = bird.getBounds();
		Rectangle topWallBounds = firstWall.getTopBounds();
		Rectangle bottomWallBounds = firstWall.getBottomBounds();
		if(birdBounds.intersects(topWallBounds) 
				|| birdBounds.intersects(bottomWallBounds)) {
			nextLife();
		}
		
		// should another wall be added?
		count += 1;
		if(count > SEPARATION) {
			Wall wall = new Wall(fm);
			walls.add(wall);
			count = 0;
		}
	
	}
	
	public Bird getBird() {
		return bird;
	}
	
	private void nextLife() {
		FileIO.playClip(this, THUD_SOUND);
		
		babyBird.nextBird();
		
		count = 0;
		
		walls.clear();
		Wall wall = new Wall(fm);
		walls.add(wall);
		
		repaint();
	}
	
	public void restart() {
		count = 0;
		bird = new Bird(HEIGHT);
		walls.clear();
		Wall wall = new Wall(fm);
		walls.add(wall);
		repaint();
	}

}
