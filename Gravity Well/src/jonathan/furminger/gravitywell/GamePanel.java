package jonathan.furminger.gravitywell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import jonathan.furminger.mycomponents.ScorePanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 400;
	private ScorePanel scorePanel;

	
	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
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
		
		
		// landscape
		
		
		// landing pads
		
		
		// lander
	}
}
