package jonathan.furminger.watchyourstep;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class TerrainButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	private static final int SIZE = 50;
	private int row, col, nextToHoles = 0;
	private boolean hole, revealed = false;
	
	public TerrainButton(int row, int col) {
		this.row = row;
		this.col = col;
		Dimension size = new Dimension(SIZE, SIZE);
		setPreferredSize(size);
		
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean hasHole() {
		return hole;
	}
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public void setHole(boolean hasHole) {
		hole = hasHole;
	}
	
	public void increaseHoleCount() {
		nextToHoles += 1;
	}
	
	public boolean isNextToHoles() {
		return nextToHoles > 0;
	}
	
	public void reveal(boolean reveal) {
		revealed = reveal;
		if(revealed) {
			if(hasHole()) {
				setBackground(Color.BLACK);
			}
			else {
				setBackground(Color.CYAN);
				if(nextToHoles > 0) {
					setText("" + nextToHoles);
				}
			}
		}
		else {
			setBackground(null);
			setText("");
		}
		setFocusPainted(false);
	}
	
	public void reset() {
		hole = false;
		revealed = false;
		nextToHoles = 0;
		setText("");
		setBackground(null);
	}
}