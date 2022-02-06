package jonathan.furminger.wordbuilder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class LetterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Color BROWN = new Color(49, 22, 3);
	private static final String IMAGENAME = "WoodTile.jpg";
	
	private String letter = "";
	private int points = -1;
	private BufferedImage image = null;
	private int size = 40;
	private Font bigFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private Font smallFont = new Font(Font.DIALOG, Font.BOLD, 12);

	public LetterPanel(String letter, int points) {
		this.letter = letter;
		this.points = points;
		initPanel();
	}
	
	public LetterPanel() {
		initPanel();
	}
	
	public void paintComponent(Graphics g) {
		if(letter.length() == 0) {
			g.setColor(BROWN);
			g.fillRect(0, 0, size, size);
		}
		else {
			g.setColor(Color.BLACK);
			g.drawRect(0,  0,  size-1,  size-1);
		}
	}
}
