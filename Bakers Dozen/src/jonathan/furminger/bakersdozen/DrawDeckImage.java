package jonathan.furminger.bakersdozen;

import java.awt.image.BufferedImage;

public class DrawDeckImage {

	public static void main(String[] args) {
		String[] suits = Deck.getSuitSymbols();
		String[] ranks = Deck.getRanks();
		int cardWidth = Deck.getCardWidth();
		int cardHeight = Deck.getCardHeight();
		
		int imageWidth = cardWidth * 13;
		int imageHeight = cardHeight * 4;
		
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		

	}

}
