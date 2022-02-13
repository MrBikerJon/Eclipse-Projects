package jonathan.furminger.bakersdozen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int CARDWIDTH = Deck.getCardWidth();
	private static final int CARDHEIGHT = Deck.getCardHeight();
	private static final int SPACING = 4;
	private static final int MARGIN = 10;
	private static final int WIDTH = CARDWIDTH * 13 + SPACING * 12 + MARGIN * 2;
	private static final int HEIGHT = CARDHEIGHT * 9 + MARGIN * 3;
	private static final int FOUNDATIONX = WIDTH/2-(4*CARDWIDTH + 3*SPACING) /2;
	private static final int FOUNDATIONY = MARGIN;
	private static final int BOARDX = MARGIN;
	private static final int BOARDY = CARDHEIGHT+MARGIN+MARGIN;
	private static final int OVERLAP = (int) (CARDHEIGHT*.65);
	
	private Deck deck;
	CardStack[] foundation = new CardStack[4];
	CardStack[] column = new CardStack[13];
	
	public TablePanel() {
		int x = FOUNDATIONX;
		int y = FOUNDATIONY;
		for(int i = 0; i < 4; i++) {
			foundation[i] = new CardStack(x, y, 0);
			x += CARDWIDTH + SPACING;
		}
		x = BOARDX;
		y = BOARDY;
		for(int i = 0; i < 13; i++) {
			column[i] = new CardStack(x, y, OVERLAP);
			x+= CARDWIDTH + SPACING;
		}
		deck = new Deck();
		deal();
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}

	private void deal() {
		// deal 4 rows of 13 columns
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 13; col++) {
				Card card = deck.deal();
				column[col].add(card);
			}
		}
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		// draw background
		g.setColor(Color.GREEN);
		g.fillRect(0,  0,  WIDTH,  HEIGHT);
		
		// draw foundation
		
		
		// draw board
		
		
		// draw moving card
	}
}
