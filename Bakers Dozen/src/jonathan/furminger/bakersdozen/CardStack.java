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
}
