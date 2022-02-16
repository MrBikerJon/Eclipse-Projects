package jonathan.furminger.catandmouse;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Mouse {
	// directions in clockwise rotation order
	public static final int DIRECTION_UP = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_DOWN = 2;
	public static final int DIRECTION_LEFT = 3;
	public static final int DIRECTION_NONE = 4;
	
	private static final String MOUSE_UP = "/mouseUp.gif";
	private static final String MOUSE_RIGHT = "/mouseRight.gif";
	private static final String MOUSE_DOWN = "/mouseDown.gif";
	private static final String MOUSE_LEFT = "/mouseLeft.gif";
	
	private GamePanel gamePanel;
	private Maze maze;
	private int x;
	private int y;
	private int direction;
	private BufferedImage[] image = new BufferedImage[4];
	private int[] offsetX = new int[4];
	private int[] offsetY = new int[4];
	
	public Mouse (GamePanel gamePanel, Maze maze) {
		this.gamePanel = gamePanel;
		this.maze = maze;
		x = maze.getMouseX();
		y = maze.getMouseY();
		direction = DIRECTION_DOWN;
		
		try {
			setImage(DIRECTION_UP, MOUSE_UP);
			setImage(DIRECTION_DOWN, MOUSE_DOWN);
			setImage(DIRECTION_LEFT, MOUSE_LEFT);
			setImage(DIRECTION_RIGHT, MOUSE_RIGHT);
		}
		catch (IOException e) {
			String message = "The file could not be read";
			JOptionPane.showMessageDialog(null, message);
			System.exit(5);
		}
	}

	private void setImage (int direction, String fileName) throws IOException {
		InputStream input = getClass().getResourceAsStream(fileName);
		image[direction] = ImageIO.read(input);
		int imageWidth = image[direction].getWidth();
		offsetX[direction] = Maze.CELL_SIZE / 2 - imageWidth;
		int imageHeight = image[direction].getHeight();
		offsetY[direction] = Maze.CELL_SIZE / 2 - imageHeight;
	}
	
	public void draw(Graphics g) {
		int xPos = x + offsetX[direction];
		int yPos = y + offsetY[direction];
		g.drawImage(image[direction], xPos, yPos, null);
		
	}
	
	public void turn(int direction) {
		this.direction = direction;
	}
}
