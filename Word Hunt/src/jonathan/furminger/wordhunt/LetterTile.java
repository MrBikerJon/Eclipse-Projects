package jonathan.furminger.wordhunt;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class LetterTile {
	public static final int SIZE = 75;
	public static final int HALF_SIZE = SIZE / 2;
	
	private String letter = "";
	private FontMetrics fm;
	private int x = -1;
	private int y = -1;
	private boolean selected = false;
	private int centerX = -1;
	private int centerY = -1;
	
	public LetterTile(String letter, FontMetrics fm, int row, int col) {
		if (letter.equals("Q")) {
			letter = "Qu";
		}
		this.letter = letter;
		this.fm = fm;
		int left = col * SIZE;
		int letterWidth = fm.stringWidth(letter);
		int fromLeft = (SIZE - letterWidth) / 2;
		x = left + fromLeft;
		int top = row * SIZE;
		int fromTop = (SIZE * 3) / 4;
		y = top + fromTop;
		
		centerX = left + HALF_SIZE;
		centerY = top + HALF_SIZE;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(letter, x, y);
	}
	
	public void select() {
		selected = true;
	}
	
	public void unselect() {
		selected = false;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public int getCenterX() {
		return centerX;
	}
	
	public int getCenterY() {
		return centerY;
	}

}
