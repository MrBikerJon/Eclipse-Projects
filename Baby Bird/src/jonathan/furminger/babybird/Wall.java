package jonathan.furminger.babybird;

import java.awt.image.BufferedImage;

import jonathan.furminger.mycommonmethods.FileIO;

public class Wall {
	private static final String WALL_IMAGE_FILE = "/wall.jpg";
	
	private static BufferedImage wallImage;
	private static int width = 72;
	private static int height = 600;
	
	private int x = FlightPanel.WIDTH;
	private int bottomY;
	private int topHeight;
	private int bottomHeight;
	private BufferedImage topImage;
	private BufferedImage bottomImage;
	
	public Wall() {
		if(wallImage == null) {
			wallImage = FileIO.readImageFile(this, WALL_IMAGE_FILE);
			width = wallImage.getWidth();
			height = wallImage.getHeight();
		}
	}
	
}
