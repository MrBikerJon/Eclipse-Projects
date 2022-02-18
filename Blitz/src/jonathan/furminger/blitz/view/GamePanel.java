package jonathan.furminger.blitz.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	private static final int DECK_X = (WIDTH / 2) - (CARD_WIDTH / 2) - (SPACING / 2);
	private static final int DECK_Y = MARGIN + PLAYER_HEIGHT + GAP;
	private static final int DISCARD_X = DECK_X + CARD_WIDTH + SPACING;
	private static final int DISCARD_Y = DECK_Y;
	private static final int[] PLAYER_X = {MARGIN, MARGIN + PLAYER_WIDTH + GAP, WIDTH / 2 - PLAYER_WIDTH / 2};
	private static final int[] PLAYER_Y = {MARGIN + FONT_SIZE, MARGIN + FONT_SIZE, DECK_Y + CARD_HEIGHT + FONT_SIZE};
	
	private BufferedImage deck;
	private BufferedImage discard;
	private PlayerView[] players = new PlayerView[3];
	private BufferedImage movingCard;
	private int cardX = 0;
	private int cardY = 0;
	private boolean cardIsMoving = false;
	
	public GamePanel(BlitzController controller, BufferedImage cardBackImage) {
		Font font = new Font(Font.DIALOG, Font.BOLD, FONT_SIZE);
		setFont(font);
		deck = cardBackImage;
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// players
		g.setColor(Color.BLACK);
		for(int i = 0; i < players.length; i++) {
			if(players[i] != null) {
				players[i].draw(g);
			}
		}
		
		// deck
		g.drawImage(deck, DECK_X, DECK_Y, null);
		
		
		// discard
		g.drawImage(discard, DISCARD_X, DISCARD_Y, null);
		
		// moving card
		if(cardIsMoving) {
			g.drawImage(movingCard, cardX, cardY, null);
			
		}
		
	}
	
	public void setDiscard(BufferedImage discard) {
		this.discard = discard;
		repaint();
	}
	
	public void setPlayer(int index, String name, int tokens) {
		PlayerView playerView = new PlayerView(name, tokens, PLAYER_X[index], PLAYER_Y[index]);
		players[index] = playerView;
		repaint();
	}
	
	public void updateCardsForPlayer(int playerIndex, ArrayList<BufferedImage> newCards) {
		players[playerIndex].updateCards(newCards);
		repaint();
	}
	
	public void addInfoForPlayer(int playerIndex, String additionalInfo) {
		players[playerIndex].addInfo(additionalInfo);;
		repaint();
	}
	
	public void moveDeckToPlayer(BufferedImage movingCardImage, int playerIndex, int cardIndex) {
		int beginX = DECK_X;
		int beginY = DECK_Y;
		int offsetX = cardIndex * (CARD_WIDTH + SPACING);
		int endX = PLAYER_X[playerIndex] + offsetX;
		int endY = PLAYER_Y[playerIndex] + SPACING;
		
		movingCard = movingCardImage;
		
		moveCard(beginX, beginY, endX, endY);
	}
	
	private void moveCard(int beginX, int beginY, int endX, int endY) {
		//determine speed and direction
		int incrementX = 8;
		int incrementY = 8;
		if(beginX > endX) {
			incrementX = -8;
		}
		if(beginY > endY) {
			incrementY = -8;
		}
		
		// set initial location of moving card
		cardIsMoving = true;
		cardX = beginX;
		cardY = beginY;
		
		// move the card
		while(cardIsMoving) {
			paintImmediately(0, 0, WIDTH, HEIGHT);

			try {
				Thread.sleep(60);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			cardIsMoving = false;
			cardX += incrementX;
			cardY += incrementY;
			
			// stop X movement if went too far
			if(incrementX > 0 && cardX > endX 
					|| incrementX < 0 && cardX < endX) {
				cardX = endX;
			}
			else {
				cardIsMoving = true;
			}
			
			// stop Y movement if went too far
			if(incrementY > 0 && cardY > endY 
					|| incrementY < 0 && cardY < endY) {
				cardY = endY;
			}
			else {
				cardIsMoving = true;
			}
		}
	}
	
	public void moveDiscardToPlayer(BufferedImage movingCardImage, int playerIndex, int cardIndex) {
		int beginX = DISCARD_X;
		int beginY = DISCARD_Y;
		int offsetX = cardIndex * (CARD_WIDTH + SPACING);
		int endX = PLAYER_X[playerIndex] + offsetX;
		int endY = PLAYER_Y[playerIndex] + SPACING;
		movingCard = movingCardImage;
		moveCard(beginX, beginY, endX, endY);
	}
	
	public void movePlayerToDiscard(BufferedImage movingCardImage, int playerIndex, int cardIndex) {
		int offsetX = cardIndex * (CARD_WIDTH + SPACING);
		int beginX = PLAYER_X[playerIndex] + offsetX;
		int beginY = PLAYER_Y[playerIndex] + SPACING;
		int endX = DISCARD_X;
		int endY = DISCARD_Y;
		movingCard = movingCardImage;
		moveCard(beginX, beginY, endX, endY);
	}
	
	public void moveDeckToDiscard(BufferedImage movingCardImage) {
		movingCard = movingCardImage;
		moveCard(DECK_X, DECK_Y, DISCARD_X, DISCARD_Y);
	}
	
}
