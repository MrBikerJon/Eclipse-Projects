package jonathan.furminger.gravitywell;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import jonathan.furminger.mycommonmethods.FileIO;

public class Lander {
	private static final String IMAGE_DRIFT = "/lander.gif";
	private static final String IMAGE_LEFT = "/landerLeft.gif";
	private static final String IMAGE_RIGHT = "/landerRight.gif";
	private static final String IMAGE_UP = "/landerUp.gif";
	private static final int MOVE_DRIFT = 0;
	private static final int MOVE_LEFT = 1;
	private static final int MOVE_RIGHT = 2;
	private static final int MOVE_UP = 3;
	private static final int SIZE = 48;
	private static final double START_X = 50d;
	private static final double START_Y = 10d;
	private static final double MAX_LANDING_SPEED = .25d;
	private static final double GRAVITY = .003d;
	private static final double ACCELERATION = .015d;
	
	private BufferedImage[] image = new BufferedImage[4];
	private int imageIndex = MOVE_DRIFT;
	private double x = START_X;
	private double y = START_Y;
	private FuelGauge fuelGauge = new FuelGauge();
	private Speedometer speedometer = new Speedometer(MAX_LANDING_SPEED);
	private double moveX = 0;
	private double moveY = 0;
	
	public Lander() {
		image[MOVE_DRIFT] = FileIO.readImageFile(this, IMAGE_DRIFT);
		image[MOVE_LEFT] = FileIO.readImageFile(this, IMAGE_LEFT);
		image[MOVE_RIGHT] = FileIO.readImageFile(this, IMAGE_RIGHT);
		image[MOVE_UP] = FileIO.readImageFile(this, IMAGE_UP);
	}
	
	public void draw(Graphics g) {
		// lander
		int xPos = (int) x;
		int yPos = (int) y;
		// allow for extra thruster blast for right moving
		// lander
		if(imageIndex == MOVE_RIGHT) {
			xPos -= 4;
		}
		g.drawImage(image[imageIndex], xPos, yPos, null);
		
		// fuel gauge
		fuelGauge.draw(g);
		
		// speedometer
		speedometer.draw(g);
	}
	
	public void move() {
		moveY += GRAVITY;
		x += moveX;
		y += moveY;
		
		speedometer.setSpeed(moveY);
	}
	
}
