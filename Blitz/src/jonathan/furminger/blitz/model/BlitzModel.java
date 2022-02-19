package jonathan.furminger.blitz.model;

import java.util.Random;

import jonathan.furminger.blitz.controller.BlitzController;

public class BlitzModel {
	private static final int STATE_WAIT_FOR_INPUT = 0;
	private static final int STATE_NEW_HAND = 1;
	private static final int STATE_DEAL = 2;
	private static final int STATE_COMPUTER_DRAW = 3;
	private static final int STATE_COMPUTER_DISCARD = 4;
	private static final int STATE_NEXT_PLAYER = 5;
	private static final int STATE_MY_TURN_DRAW = 6;
	private static final int STATE_MY_TURN_DISCARD = 7;
	private static final int STATE_SETTLE_RAP = 8;
	private static final int STATE_SETTLE_BLITZ = 9;
	private static final int STATE_END_OF_HAND = 10;

	private BlitzController controller;
	private int numberOfPlayers;
	private Player[] players;
	private int myPlayerId;
	private Random rand = new Random();
	private int dealerId;
	private int state = STATE_WAIT_FOR_INPUT;
	private int active = 0;
	private Player player;
	private boolean someoneRapped = false;
	private Deck deck = new Deck();
	private DiscardStack discardStack = new DiscardStack();
	
	public BlitzModel(BlitzController controller, int numberOfPlayers) {
		this.controller = controller;
		this.numberOfPlayers = numberOfPlayers;
		players = new Player[numberOfPlayers];
		myPlayerId = numberOfPlayers - 1;
	
		// the last player is human
		int computerPlayers = numberOfPlayers - 1;
		for(int id = 0; id < computerPlayers; id++) {
			String name = "Player " + (id + 1);
			players[id] = new Player(id, name, false, true);
		}
		players[myPlayerId] = new Player(myPlayerId, "You", true, true);
		
		dealerId = rand.nextInt(numberOfPlayers);
	}
	
	public Player getPlayer(int index) {
		return players[index];
	}
	
	public int getMyPlayerId() {
		return myPlayerId;
	}
	
	public Player getDealer() {
		return players[dealerId];
	}
	
	public void play() {
		switch(state) {
		case STATE_NEW_HAND :
			newHand();
			break;
		case STATE_DEAL :
			deal();
			break;
		case STATE_NEXT_PLAYER :
			nextPlayer();
			break;
		case STATE_COMPUTER_DRAW :
			computerDraw();
			break;
		case STATE_COMPUTER_DISCARD :
			computerDiscard();
			break;
		case STATE_SETTLE_RAP :
			settleRap();
			break;
		case STATE_SETTLE_BLITZ :
			settleBlitz();
			break;
		case STATE_END_OF_HAND :
			endOfHand();
			break;
		
		}
	}
	
	private void newHand() {
		active = dealerId;
		player = players[active];
		someoneRapped = false;
		deck.reset();
		discardStack.reset();
		for(int i = 0; i < numberOfPlayers; i++) {
			players[i].startNewHand();
		}
		controller.showNewHand();
		state = STATE_DEAL;
		play();
	}
	
	private void deal() {
		// deal 3 cards to each player
		for(int c = 0; c < 3; c++) {
			for(int p = 0; p < numberOfPlayers; p++) {
				int dealTo = (dealerId + p + 1) % numberOfPlayers;
				if(!players[dealTo].isOut()) {
					Card card = deck.deal();
					players[dealTo].addCard(card);
					controller.showDrawCard(players[dealTo], card);	
				}
			}
		}
		
		// deal a discard
		Card card = deck.deal();
		discardStack.add(card);
		controller.showDealDiscard(card);
		state = STATE_NEXT_PLAYER;
		play();
	}
	
	private void nextPlayer() {
		active = (active + 1) % numberOfPlayers;
		player = players[active];
		if(player.isOut()) {
			state = STATE_NEXT_PLAYER;
		}
		else if(player.hasRapped()) {
			state = STATE_SETTLE_RAP;
		}
		else if(player.isHuman()) {
			state = STATE_MY_TURN_DRAW;
			controller.showMyTurn();
		}
		else {
			state = STATE_COMPUTER_DRAW;
		}
		play();
	}
	
	private void computerDraw() {
		boolean shouldTakeDiscard = false;
		// if the discard stack is not empty
		if(!discardStack.isEmpty()) {
			// check the points in the hand without the discard
			int priorPoints = player.getPointsInHand();
			// add the discard to the hand
			Card discard = discardStack.getTopCard();
			player.addCard(discard);
			// remove the lowest card in the hand
			int lowestCardIndex = player.getLowestCardIndex();
			Card removedCard = player.removeCardAt(lowestCardIndex);
			// check the points in the hand again
			int afterPoints = player.getPointsInHand();
			// if there are now more points, it improved the hand
			if(priorPoints < afterPoints) {
				shouldTakeDiscard = true;
			}
			// put the cards back as they were
			player.addCard(removedCard);
			player.removeCard(discard);
		}
		System.out.println("Should take discard == " + shouldTakeDiscard);  // TODO remove later
		if(shouldTakeDiscard) {
			Card discard = discardStack.removeTopCard();
			player.addCard(discard);
			Card nextDiscard = discardStack.getTopCard();
			controller.showTakeDiscard(player, discard, nextDiscard);
			state = STATE_COMPUTER_DISCARD;
		}
	}
	
	private void computerDiscard() {
		
	}
	
	private void settleRap() {
		
	}
	
	private void settleBlitz() {
		
	}
	
	private void endOfHand() {
		
	}
	
	public void start() {
		state = STATE_NEW_HAND;
		play();
	}
	
	public boolean hasSomeoneRapped() {
		return someoneRapped;
	}
	
}
