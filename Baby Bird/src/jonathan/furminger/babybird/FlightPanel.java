package jonathan.furminger.babybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class FlightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	private BabyBird babyBird;
	private Bird bird = new Bird();
	
	public FlightPanel(BabyBird babyBird) {
		this.babyBird = babyBird;
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char key = e.getKeyChar();
				if(key == ' ') {
					bird.startFlapping();
					bird.move();
					repaint();
				}
			}
		});
		
		// listeners
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
		
		
	}

}
