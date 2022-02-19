package jonathan.furminger.blitz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.blitz.view.BlitzViewWindow;
import jonathan.furminger.blitz.view.GamePanel;
import jonathan.furminger.mycommonmethods.FileIO;

public class BlitzController {

	private static final String CARDS_FILE = "/cards.png";
	private static final String CARD_BACK_FILE = "/cardBack.png";
	public static final int CARD_WIDTH = 30;
	public static final int CARD_HEIGHT = 50;
	private static final int SUITS = 4;
	private static final int RANKS = 13;
	private static final int CARD_BACK_INDEX = SUITS * RANKS;
	private static final int NUMBER_OF_PLAYERS = 3;
	private static final int NUMBER_OF_IMAGES = SUITS * RANKS + 1;
	private BufferedImage[] cardImages = new BufferedImage[NUMBER_OF_IMAGES];
	
	private BlitzViewWindow window;
	private GamePanel gamePanel;
	
	public BlitzController() {
		readCardImages();
		window = new BlitzViewWindow(this, cardImages[CARD_BACK_INDEX]);
		gamePanel = window.getGamePanel();
	
	}
	
	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BlitzController();
			}
		});
	}
	
	private void readCardImages() {
		BufferedImage cardsImage = FileIO.readImageFile(this, CARDS_FILE);
		int i = 0;
		for(int suit = 0; suit < SUITS; suit++) {
			for(int rank = 0; rank < RANKS; rank++) {
				int x = rank * CARD_WIDTH;
				int y = suit * CARD_HEIGHT;
				cardImages[i] = cardsImage.getSubimage(x, y, CARD_WIDTH, CARD_HEIGHT);
				i++;
			}
		}
		cardImages[CARD_BACK_INDEX] = FileIO.readImageFile(this, CARD_BACK_FILE);
	}
	
	public ActionListener getDealListener() {
		ActionListener dealListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.enableDealButton(false);
				window.setDealButtonText("Next Player");
				
				// test code
				Random rand = new Random();
				int numberOfCards = SUITS * RANKS;
				int pick = rand.nextInt(numberOfCards);
				gamePanel.setDiscard(cardImages[pick]);
				
				gamePanel.setPlayer(0, "Tom", 3);
				gamePanel.setPlayer(1, "Dick", 0);
				gamePanel.setPlayer(2,  "Harry", -1);
				
				// pick 3 random cards for each player
				for(int p = 0; p < NUMBER_OF_PLAYERS; p++) {
					ArrayList<BufferedImage> newCards = new ArrayList<BufferedImage>();
					for(int c = 0; c < 3; c++) {
						pick = rand.nextInt(numberOfCards);
						newCards.add(cardImages[pick]);
					}
					gamePanel.updateCardsForPlayer(p, newCards);

				}
				gamePanel.addInfoForPlayer(0, "Rapped");
				BufferedImage movingCardImage = cardImages[12];
				gamePanel.moveDeckToPlayer(movingCardImage, 1, 3);
				gamePanel.moveDiscardToPlayer(movingCardImage, 0, 0);
				gamePanel.movePlayerToDiscard(movingCardImage, 2, 1);
				gamePanel.moveDeckToDiscard(movingCardImage);
				gamePanel.updateTokensForPlayer(0, 1);
				gamePanel.addInfoForPlayer(0, "Lost 2");
				gamePanel.clearCardsAndInfo();
			}
		};
		
		return dealListener;
	}
	
	public ActionListener getRapListener() {
		ActionListener rapListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.enableRapButton(false);
			}
		};
		
		return rapListener;
	}
	
	public MouseAdapter getMouseAdapter() {
		MouseAdapter adapter = new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				clicked(x, y);
			}
		};
		return adapter;
	}
	
	private void clicked(int x, int y) {
		if(gamePanel.hasDeckAt(x,  y) ) {
			System.out.println("The deck was clicked");
		}
		if(gamePanel.hasDiscardAt(x, y)) {
			System.out.println("The discard was clicked");
		}
		int clickedCard = gamePanel.getCardIndexForPlayerAt(2, x, y);
		if(clickedCard > -1) {
			System.out.println("Card: " + clickedCard);
		}
	}

}
