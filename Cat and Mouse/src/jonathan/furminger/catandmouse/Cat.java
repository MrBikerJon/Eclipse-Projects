package jonathan.furminger.catandmouse;

import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

public class Cat extends MazeRunner {

	private static final String CAT_UP = "/catUp.png";
	private static final String CAT_DOWN = "/catDown.png";
	private static final String CAT_LEFT = "/catLeft.png";
	private static final String CAT_RIGHT = "/catRight.png";
	
	public static final int SPEED_WANDER = 5;
	public static final int SPEED_HUNT = 10;
	public final CatWanderState STATE_WANDER = new CatWanderState(this);
	public final CatHuntState STATE_HUNT = new CatHuntState(this);
	
	private Random rand = new Random();

	private Mouse mouse;
	
	public Cat(Mouse mouse, Maze maze) {
		this.mouse = mouse;
		this.maze = maze;
		
		x = maze.getCatX();
		y = maze.getCatY();
		direction = DIRECTION_LEFT;
		changeX = -1;
		changeY = 0;
		speed = SPEED_WANDER;
		state = STATE_WANDER;
		
		try {
			setImage(DIRECTION_UP, CAT_UP);
			setImage(DIRECTION_DOWN, CAT_DOWN);
			setImage(DIRECTION_LEFT, CAT_LEFT);
			setImage(DIRECTION_RIGHT, CAT_RIGHT);
		}
		catch (IOException e) {
			String message = "Could not read cat image files";
			JOptionPane.showMessageDialog(null, message);
			System.exit(6);
		}
	}
	
	public void  setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void followPath() {
		if(!wallInDirection(direction)) {
			// continue in current direction
			x += changeX * speed;
			y += changeY * speed;
		}
		else {
			moveIntoCell();
			// randomly choose a left or right turn
			int right = (direction + 1) % 4;
			int left = (direction + 3) % 4;
			int pick = rand.nextInt(2);
			
			if(pick == 0) {
				// try a right turn first
				if(!wallInDirection(right)) {
					turn(right);
				}
				else {
					turn(left);
				}
			}
			else {
				// try a left turn first
				if(!wallInDirection(left)) {
					turn(left);
				}
				else {
					turn(right);
				}
			}
		}
	}
	
	public void hunt() {
		followPath();
	}
	
}
