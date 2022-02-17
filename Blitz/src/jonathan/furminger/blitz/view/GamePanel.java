package jonathan.furminger.blitz.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import jonathan.furminger.blitz.controller.BlitzController;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int SPACING = 4;
	private static final int GAP = 20;
	private static final int MARGIN = 10;
	public static final int FONT_SIZE = 20;
	private static final int CARD_WIDTH = BlitzController.CARD_WIDTH;
	private static final int CARD_HEIGHT = BlitzController.CARD_HEIGHT;
	private static final int PLAYER_WIDTH =(CARD_WIDTH * 4) + (SPACING * 3);
	private static final int PLAYER_HEIGHT = CARD_HEIGHT + FONT_SIZE + (SPACING * 3);
	private static final int WIDTH = (2 * PLAYER_WIDTH) + GAP + (MARGIN * 2);
	private static final int HEIGHT = (2 * PLAYER_HEIGHT) + CARD_HEIGHT + (GAP * 2) + (MARGIN * 2);
	
	public GamePanel(BlitzController controller) {
		Font font = new Font(Font.DIALOG, Font.BOLD, FONT_SIZE);
		setFont(font);
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// players
		
		
		// deck
		
		
		// discard
		
		
		// moving card
		
	}
	
	
}
