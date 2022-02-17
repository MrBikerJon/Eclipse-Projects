package jonathan.furminger.blitz.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import jonathan.furminger.blitz.controller.BlitzController;

public class PlayerView {

	private static final int CARD_WIDTH = BlitzController.CARD_WIDTH;
	private static final int CARD_HEIGHT = BlitzController.CARD_HEIGHT;
	private static final int SPACING = GamePanel.SPACING;
	private static final int FONT_SIZE = GamePanel.FONT_SIZE;
	private static final int TOKEN_SIZE = 10;
	private static final int TOKEN_Y_OFFSET = CARD_HEIGHT + SPACING;
	private static final int TOKEN_TEXT_Y_OFFSET = TOKEN_Y_OFFSET + FONT_SIZE;
	private static final int INFO_Y_OFFSET = TOKEN_TEXT_Y_OFFSET + SPACING + FONT_SIZE;
	
	private String name;
	private int tokens;
	private int x;
	private int y;
	private ArrayList<BufferedImage> cards = new ArrayList<BufferedImage>();
	
	public PlayerView (String name, int tokens, int x, int y) {
		this.name = name;
		this.tokens = tokens;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		// name
		g.drawString(name,  x,  y);
		
		// cards
		
		
		// tokens
		
		
		// info
	}
	
}
