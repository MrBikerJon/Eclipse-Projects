package jonathan.furminger.catandmouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import jonathan.furminger.mycomponents.ScorePanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final String MUSIC = "/threeBlindMice.wav";
	
	private ScorePanel scorePanel;
	private int width = 640;
	private int height = 400;
	private Maze maze;
	private Mouse mouse;
	private Timer timer;
	private Cat cat;
	private int numberOfExtraMice;
	private BufferedImage mouseImage;
	private int mouseOffsetX;
	private int mouseOffsetY;
	private Clip audioClip;
	
	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		
		maze = new Maze();
		width = maze.getWidth();
		height = maze.getHeight();
		
		initGUI();
		mouse = new Mouse(this, maze);
		cat = new Cat(mouse, maze);
		numberOfExtraMice = maze.getNumberOfExtraMice();
		mouseImage = mouse.getFirstImage();
		mouseOffsetX = mouse.getFirstOffsetX();
		mouseOffsetY = mouse.getFirstOffsetY();
		
		try {
			URL url = getClass().getResource(MUSIC);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			audioClip = AudioSystem.getClip();
			audioClip.open(audioInputStream);
		}
		catch (IOException e) {
			String message = "The file " + MUSIC + " cannot be opened.";
			JOptionPane.showMessageDialog(this, message);
		}
		catch (UnsupportedAudioFileException e) {
			String message = "The file " + MUSIC + " is not a valid audio file.";
			JOptionPane.showMessageDialog(this, message);
		}
		catch (LineUnavailableException e) {
			String message = "Resources are not available to open " + MUSIC; 
			JOptionPane.showMessageDialog(this, message);
		}

		timer.start();
		audioClip.loop(Clip.LOOP_CONTINUOUSLY);
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
		for(int i = 0; i < numberOfExtraMice; i++) {
			int x = maze.getExtraMouseX(i) + mouseOffsetX;
			int y = maze.getExtraMouseY(i) + mouseOffsetY;
			g.drawImage(mouseImage, x, y, null);
		}
		
		// cat
		cat.draw(g);
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
					mouse.setState(mouse.STATE_RUN);
					cat.setState(cat.STATE_HUNT);
					repaint();
				}
			}
			
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				if(code == KeyEvent.VK_UP
						|| code == KeyEvent.VK_DOWN
						|| code == KeyEvent.VK_LEFT
						|| code == KeyEvent.VK_RIGHT) {
					mouse.setState(mouse.STATE_WAIT);
					cat.setState(cat.STATE_WANDER);
					repaint();
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
		cat.move();
		repaint();
		Rectangle catBounds = cat.getBounds();
		Rectangle mouseBounds = mouse.getBounds();
		if(catBounds.intersects(mouseBounds)) {
			nextMouse();
		}
	}
	
	public void increaseScore() {
		scorePanel.addToScore(1);
		if(maze.getRemainingCheese() == 0) {
			String message = "You got all the cheese!";
			endGame(message);
		}
	}
	
	public void endGame(String message) {
		timer.stop();
		audioClip.stop();
		repaint();
		message += " Do you want to play again?";
		int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			newGame();
		}
		else {
			System.exit(0);;
		}
	}
	
	private void newGame() {
		maze.reset();
		scorePanel.reset();
		mouse = new Mouse(this, maze);
		cat = new Cat(mouse, maze);
		numberOfExtraMice = maze.getNumberOfExtraMice();
		timer.start();
		audioClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	private void nextMouse() {
		timer.stop();
		audioClip.stop();
		numberOfExtraMice--;
		if(numberOfExtraMice >= 0) {
			String message = "The mouse got caught!";
			JOptionPane.showMessageDialog(this, message);
			mouse = new Mouse(this, maze);
			cat = new Cat(mouse, maze);
			timer.start();
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			String message = "Game Over!";
			endGame(message);
		}
	}

}
