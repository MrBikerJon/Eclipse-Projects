package jonathan.furminger.fallingbricks;

import java.awt.image.BufferedImage;

import jonathan.furminger.mycommonmethods.FileIO;

public class SBrick extends Brick {
	private static final String BRICK_FILE = "/redBrick.jpg";
	private static final boolean[][] TILES = {{false, true, true}, {true, true, false}};
	
	private static BufferedImage image;
	
	public SBrick(int row, int col) {
		super(row, col);
		if(image == null) {
			image = FileIO.readImageFile(this, BRICK_FILE);
		}
	}
	
}