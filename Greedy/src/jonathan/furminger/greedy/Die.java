
		
package jonathan.furminger.greedy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Die extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 60;
	private static final int AVAILABLE = 0;
	private static final int SELECTED = 1;
	private static final int HELD = 2;
	
	private int value = 2;
	private int state = AVAILABLE;
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	// method to paint the component and label the sections for drawing different parts of the die
	public void paintComponent(Graphics g) {
		
		// fill the background
		switch(state) {
		case AVAILABLE :
			g.setColor(Color.WHITE);
			break;
		case SELECTED :
			g.setColor(Color.RED);
			break;
		case HELD :
			g.setColor(Color.LIGHT_GRAY);
			break;
		}
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		// draw a border
		g.setColor(Color.BLACK);
		g.drawRect(0,  0, WIDTH-1, HEIGHT-1);
		
		// draw the dots
		switch(value) {
		case 5 : 
			drawDot(g, WIDTH/4, HEIGHT/4);
			drawDot(g, WIDTH-(WIDTH/4), HEIGHT-(HEIGHT/4));
		case 3 :
			drawDot(g, WIDTH-(WIDTH/4), HEIGHT/4);
			drawDot(g, WIDTH/4, HEIGHT-(HEIGHT/4));
		case 1 :
			drawDot(g, WIDTH/2, HEIGHT/2);
			break;
		case 6 :
			drawDot(g, WIDTH/4, HEIGHT/2);
			drawDot(g, WIDTH/2 + WIDTH/4, HEIGHT/2);
		case 4 : 
			drawDot(g, WIDTH/4, HEIGHT/4);
			drawDot(g, WIDTH-(WIDTH/4), HEIGHT-(HEIGHT/4));
		case 2 :
			drawDot(g, WIDTH-(WIDTH/4), HEIGHT/4);
			drawDot(g, WIDTH/4, (HEIGHT-HEIGHT/4));
		}
		
		
	}
	
	private void drawDot(Graphics g, int x, int y) {
		g.fillOval(x-5, y-5, 10, 10);
	}
	
}
