package jonathan.furminger.babybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FlightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	private BabyBird babyBird;
	private Bird bird = new Bird(HEIGHT);
	private Timer timer;
	
	public FlightPanel(BabyBird babyBird) {
		this.babyBird = babyBird;
		
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
		
		
	}
	
	private void timedAction() {
		// move bird
		bird.move();
		
		// move walls
		
		// check for collision
		
		// should another wall be added?
		
		// repaint
		repaint();
	}

}
