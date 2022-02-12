package jonathan.furminger.bakersdozen;

import java.util.ArrayList;

public class CardStack {

	private ArrayList<Card> cards = new ArrayList<>();
	private int stackX = 0;
	private int stackY = 0;
	private int overlap = 0;
	
	public CardStack (int stackX, int stackY, int overlap) {
		this.stackX = stackX;
		this.stackY = stackY;
		this.overlap = overlap;
	}
	
	public void add(Card card) {
		int cardx = stackX;
		int cardy = overlap*cards.size() + stackY;
		card.setXY(cardx, cardy);
		cards.add(card);
	}
	
	public void addToBeginning(Card card) {
		card.setXY(stackX, stackY);
		cards.add(0, card);
		for(int i = 1; i < cards.size(); i++) {
			Card nextCard = cards.get(i);
			nextCard.addToXY(0, overlap);
		}
	}
}
