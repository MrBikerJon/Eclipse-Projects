package jonathan.furminger.bakersdozen;

import java.awt.Dimension;

import javax.swing.JPanel;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int CARDWIDTH = Deck.getCardWidth();
	private static final int CARDHEIGHT = Deck.getCardHeight();
	private static final int SPACING = 4;
	private static final int MARGIN = 10;
	private static final int WIDTH = CARDWIDTH * 13 + SPACING * 12 + MARGIN * 2;
	private static final int HEIGHT = CARDHEIGHT * 9 + MARGIN * 3;
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}

}
