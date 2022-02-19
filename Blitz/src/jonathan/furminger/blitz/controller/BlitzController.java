package jonathan.furminger.blitz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.blitz.model.BlitzModel;
import jonathan.furminger.blitz.model.Card;
import jonathan.furminger.blitz.model.Player;
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
	private BlitzModel model = new BlitzModel(this, NUMBER_OF_PLAYERS);
	
	public BlitzController() {
		readCardImages();
		window = new BlitzViewWindow(this, cardImages[CARD_BACK_INDEX]);
		gamePanel = window.getGamePanel();
		start();
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
				model.start();
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
	
	public void start() {
		for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			Player player = model.getPlayer(i);
			String name = player.getName();
			int tokens = player.getTokens();
			gamePanel.setPlayer(i, name, tokens);
		}
		
		Player dealer = model.getDealer();
		String dealText = dealer.getName() + " Deal";
		window.setDealButtonText(dealText);
		window.enableDealButton(true);
		gamePanel.setDiscard(null);
	}
	
	public void showNewHand() {
		gamePanel.clearCardsAndInfo();
	}
	
	private ArrayList<BufferedImage> createCardImagesForPlayer(Player player) {
		ArrayList<BufferedImage> playerCardImages = new ArrayList<BufferedImage>();
		ArrayList<Card> cards = player.getCards();
		for(int i = 0; i < cards.size(); i++) {
			BufferedImage cardImage = cardImages[CARD_BACK_INDEX];
			if(player.shouldShow()) {
				Card card = cards.get(i);
				int id = card.getId();
				cardImage = cardImages[id];
			}
			playerCardImages.add(cardImage);
		}
		return playerCardImages;
	}
	
	public void showDrawCard(Player player, Card card) {
		int cardId = CARD_BACK_INDEX;
		if(player.shouldShow()) {
			cardId = card.getId();
		}
		BufferedImage cardImage = cardImages[cardId];
		int playerId = player.getId();
		int insertAt = player.getCardPosition(card);
		ArrayList<BufferedImage> playerCards = createCardImagesForPlayer(player);
		gamePanel.moveDeckToPlayer(cardImage, playerId, insertAt);
		gamePanel.updateCardsForPlayer(playerId, playerCards);
	}
	
	public void showDealDiscard(Card card) {
		int id = card.getId();
		BufferedImage cardImage = cardImages[id];
		gamePanel.moveDeckToDiscard(cardImage);
		gamePanel.setDiscard(cardImage);
	}
	
	public void showMyTurn() {
		if(!model.hasSomeoneRapped()) {
			window.enableRapButton(true);
		}
	}
	
	public void showTakeDiscard(Player player, Card discard, Card nextDiscard) {
		BufferedImage nextDiscardImage = null;
		if(nextDiscard != null) {
			int nextDiscardId = nextDiscard.getId();
			nextDiscardImage = cardImages[nextDiscardId];	
		}
		int discardId = discard.getId();
		BufferedImage discardImage = cardImages[discardId];
		int playerId = player.getId();
		int position = player.getCardPosition(discard);
		ArrayList<BufferedImage> playerCards = createCardImagesForPlayer(player);
		gamePanel.setDiscard(nextDiscardImage);
		gamePanel.moveDiscardToPlayer(discardImage, playerId, position);
		gamePanel.updateCardsForPlayer(playerId, playerCards);
	}

}
