package jonathan.furminger.gravitywell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import jonathan.furminger.mycommonmethods.FileIO;
import jonathan.furminger.mycomponents.ScorePanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 400;
	private static final int LANDSCAPE_WIDTH = 600;
	private static final int LANDSCAPE_HEIGHT = 360;
	private static final String FILE_NAME = "/landscapePoints.txt";
	private static final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 36);
	private static final int STATE_READY = 0;
	private static final int STATE_FLYING = 1;
	private static final int STATE_SAFE_LANDING = 2;
	private static final int STATE_BAD_LANDING = 3;
	private static final int STATE_CRASHED = 4;
	
	private ScorePanel scorePanel;
	private Polygon landscape = new Polygon();
	private LandingPad[] landingPads = new LandingPad[3];
	private Lander lander = new Lander();
	private Timer timer;
	private int state = STATE_FLYING;
	
	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		
		try {
			ArrayList<String> landingPoints = FileIO.readTextFile(this, FILE_NAME);
			for(int i = 0; i < landingPoints.size(); i++) {
				String coordinates = landingPoints.get(i);
				int commaPosition = coordinates.indexOf(",");
				String xString = coordinates.substring(0, commaPosition);
				int x = Integer.parseInt(xString);
				String yString = coordinates.substring(commaPosition + 2);
				int y = Integer.parseInt(yString);
				landscape.addPoint(x, y);
			}
			
			landingPads[0] = new LandingPad(127, 218, 300, 2);
			landingPads[1] = new LandingPad(299, 397, 93, 1);
			landingPads[2] = new LandingPad(430, 509, 315, 4);
			
			initGUI();
			
			timer.start();
		}
		catch (NumberFormatException e) {
			String message = "The file cannot be converted to numbers.";
			JOptionPane.showMessageDialog(this, message);
		}
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		// background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// sky
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LANDSCAPE_WIDTH, LANDSCAPE_HEIGHT);
		
		// landscape
		g.setColor(Color.GREEN);
		g.fillPolygon(landscape);
		
		// landing pads
		for(int i = 0; i < landingPads.length; i++) {
			landingPads[i].draw(g);
		}
		
		// lander
		lander.draw(g);
	}
	
	private void initGUI() {
		setFont(BIG_FONT);
		
		// timer
		timer = new Timer(30, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timedAction();
			}
		});
		
		// listeners
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(!lander.isOutOfFuel()) {
					int code = e.getKeyCode();
					switch(code) {
					case KeyEvent.VK_RIGHT :
						// fire right thrusters to move left
						lander.accelerateLeft();
						break;
					case KeyEvent.VK_LEFT :
						// fire left thrusters to move right
						lander.accelerateRight();
						break;
					case KeyEvent.VK_DOWN :
						// fire bottom thrusters to move up
						lander.accelerateUp();
						break;
					}
					lander.move();
					repaint();
				}
			}
			
			public void keyReleased(KeyEvent e) {
				lander.stopAcceleration();
			}
		});
	}
	
	private void timedAction() {
		lander.move();
		repaint();
		
		switch(state) {
		case STATE_READY :
			ready();
			break;
		case STATE_FLYING :
			fly();
			break;
		case STATE_SAFE_LANDING :
			safeLanding();
			break;
		case STATE_BAD_LANDING :
			badLanding();
			break;
		case STATE_CRASHED :
			crashed();
			break;
		}
	}
	
	private void ready() {
		lander.nextLander();
		state = STATE_FLYING;
	}
	
	private void fly() {
		boolean landed = false;
		for(int i = 0; i < landingPads.length && !landed; i++) {
			LandingPad landingPad = landingPads[i];
			if(landingPad.contains(lander)) {
				landed = true;
				boolean safeLanding = lander.landOn(landingPad);
				if(safeLanding) {
					state = STATE_SAFE_LANDING;
				}
				else {
					state = STATE_BAD_LANDING;
				}
			}
		}
		
		if(!landed) {
			Rectangle landerBounds = lander.getBounds();
			if(landscape.intersects(landerBounds)) {
				state = STATE_CRASHED;
			}
			else if(lander.isOutOfFuel()) {
				lander.stopAcceleration();
			}
		}
	}
	
	private void safeLanding() {
		lander.stopSound();
		int points = lander.getLandingPoints();
		String message = "You landed safely. You got " + points + " points.";
		JOptionPane.showMessageDialog(this, message);
		scorePanel.addToScore(points);
		lander.addFuel(points);
		state = STATE_READY;
	}
	
	private void badLanding() {
		lander.stopSound();
		String message = "You were going too fast!";
		JOptionPane.showMessageDialog(this, message);
		state = STATE_READY;
		
	}
	
	private void crashed() {
		lander.stopSound();
		if(lander.isOutOfFuel()) {
			timer.stop();
			String message = "You ran out of fuel. Do you want to play again?";
			int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				scorePanel.reset();
				lander.reset();
				timer.start();
				state = STATE_READY;
			}
			else {
				System.exit(0);
			}
		}
		else {
			String message = "You crashed!";
			JOptionPane.showMessageDialog(this, message);
			state = STATE_READY;
		}
	}
	
}
