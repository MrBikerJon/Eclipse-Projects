package jonathan.furminger.dotsandboxes;

import java.awt.Color;
import java.awt.Graphics;

public class Box {

	public static final int SIZE = 30;
	public static final int NONE = -1;
	public static final int LEFT = 0;
	public static final int TOP = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM = 3;
	private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.GRAY};
	
	private int x;
	private int y;
	private Color color = COLORS[0];
	private boolean[] lines = {false, false, false, false};
	
	public Box(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		// box
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		
		// lines
		g.setColor(Color.BLACK);
		if(lines[LEFT]) {
			g.drawLine(x, y, x,  + SIZE);
		}
		if(lines[TOP]) {
			g.drawLine(x, y, x + SIZE, y);
		}
		if(lines[RIGHT]) {
			g.drawLine(x + SIZE, y, x + SIZE, y + SIZE);
		}
		if(lines[BOTTOM]) {
			g.drawLine(x, y + SIZE, x + SIZE, y + SIZE);
		}
		
		// dots
		g.fillRect(x - 3, y - 3, 6, 6);
		g.fillRect(x + SIZE - 3, y - 3, 6, 6);
		g.fillRect(x - 3, y + SIZE - 3, 6, 6);
		g.fillRect(x + SIZE - 3, y + SIZE - 3, 6, 6);
	}
	
}
