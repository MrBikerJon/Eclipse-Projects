package jonathan.furminger.dotsandboxes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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
			g.drawLine(x, y, x, y + SIZE);
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
	
	public int getClosestSide(int pointX, int pointY) {
		int side = NONE;
		int fromLeft = pointX % SIZE;
		int fromRight = SIZE - fromLeft;
		int fromTop = pointY % SIZE;
		int fromBottom = SIZE - fromTop;
		
		if(fromLeft <= fromRight && fromLeft <= fromTop && fromLeft <= fromBottom) {
			side = LEFT;
		}	
		
		else if(fromRight <= fromLeft && fromRight <= fromTop && fromRight <= fromBottom) {
			side = RIGHT;
		}	
		
		else if(fromTop <= fromRight && fromTop <= fromLeft && fromTop <= fromBottom) {
			side = TOP;
		}		
		
		else {
			side = BOTTOM;
		}
		
		return side;
	}
	
	public Rectangle getLine(int side) {
		Rectangle line = null;
		
		switch(side) {
		case LEFT :
			line = new Rectangle(x, y, 1, SIZE);
			break;
		case RIGHT :
			line = new Rectangle(x + SIZE, y, 1, SIZE);
			break;
		case TOP :
			line = new Rectangle(x, y, SIZE, 1);
			break;
		case BOTTOM :
			line = new Rectangle(x, y + SIZE, SIZE, 1);
		}
		
		return line;
	}
	
	public boolean addLine(int side, boolean myLine) {
		boolean added = true;
		if(lines[side]) {
			added = false;
		}
		else {
			lines[side] = true;
			if (isCompleted()) {
				if(myLine) {
					color = COLORS[1];
				}
				else {
					color = COLORS[2];
				}
			}
		}
		return added;
	}
	
	public boolean hasLine(int side) {
		return lines[side];
	}
	
	public boolean isCompleted() {
		return lines[0] && lines[1] && lines[2] && lines[3];
	}
	
}
