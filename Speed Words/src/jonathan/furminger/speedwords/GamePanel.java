package jonathan.furminger.speedwords;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;
	private SpeedWords speedWords;
	
	public GamePanel(SpeedWords speedWords) {
		this.speedWords = speedWords;
	}

	public void paintComponent(Graphics g) {
		
		// drawing the background
		
		g.setColor(SpeedWords.TAN);
		g.fillRect(0, 0, WIDTH,  HEIGHT);
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	
}
