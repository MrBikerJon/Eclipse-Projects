package jonathan.furminger.speedwords;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import jonathan.furminger.mycommonmethods.FileIO;

public class LetterTile extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int SIZE = 40;	
	private static final String IMAGE_NAME = "/WoodTile.jpg";
	private static final Font BIG_FONT = new Font(Font.DIALOG, Font.BOLD, 30);
	private static final Font SMALL_FONT = new Font(Font.DIALOG, Font.BOLD, 12);
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int[] LETTER_POINTS = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};


	private String letter;
	private static BufferedImage image;
	private FontMetrics bigFM = getFontMetrics(BIG_FONT);
	private FontMetrics smallFM = getFontMetrics(SMALL_FONT);
	private int points;
	
	public LetterTile(String letter) {
		this.letter = letter;
		if(image == null) {
			image = FileIO.readImageFile(this, IMAGE_NAME);
		}
		int index = ALPHABET.indexOf(letter);
		points = LETTER_POINTS[index];
	}
	
	public void draw(Graphics g, int x, int y) {
		if(image == null) {
		g.setColor(Color.WHITE);
		g.fillRect(x,  y,  SIZE,  SIZE);
		} else {
			g.drawImage(image, x, y, SIZE, SIZE, null);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE-1, SIZE-1);
		
		g.setFont(BIG_FONT);
		int letterWidth = bigFM.stringWidth(letter);
		int letterX = x+((SIZE-letterWidth)/2);
		int letterY = ((SIZE*3)/4)+y;
		g.drawString(letter, letterX, letterY);
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(SIZE, SIZE);
		return size;
	}

}
