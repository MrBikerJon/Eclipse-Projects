package jonathan.furminger.catandmouse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import jonathan.furminger.mycomponents.ScorePanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ScorePanel scorePanel;
	private int width = 640;
	private int height = 400;
	
	public GamePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
		
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
		
		
		// mouse
		
		
		// extra mice
		
		
		// cat
	}

}
