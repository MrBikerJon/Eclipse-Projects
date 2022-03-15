package jonathan.furminger.wordhunt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WordHunt wordHunt;
	private int width = LetterTile.SIZE * 5;
	private int height = LetterTile.SIZE * 5;
	
	private static final Font FONT = new Font(Font.DIALOG, Font.BOLD, 50);
	private LetterTile[][] tiles = new LetterTile[5][5];
	private FontMetrics fm;
	
	public GamePanel(WordHunt wordHunt) {
		this.wordHunt = wordHunt;
		fm = getFontMetrics(FONT);
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				tiles[row][col] = new LetterTile("A", fm, row, col);
			}
		}
		initGUI();
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width, height);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		// background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		// letter tiles
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				tiles[row][col].draw(g);;
			}
		}
	}
	
	private void initGUI() {
		setFont(FONT);
	}

}
