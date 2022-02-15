package jonathan.furminger.gravitywell;

import java.awt.Color;
import java.awt.Graphics;

public class FuelGauge {
	private static final int X = 10;
	private static final int Y = 360;
	private static final int WIDTH = 620;
	private static final int HEIGHT = 20;
	private static final double FULL_TANK = 1000d;
	
	private double currentAmount = FULL_TANK;
	private int currentWidth = WIDTH;
	
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(X, Y, currentWidth, HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(X, Y, WIDTH, HEIGHT);
	}
}
