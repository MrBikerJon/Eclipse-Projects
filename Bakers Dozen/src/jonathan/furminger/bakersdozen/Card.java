package jonathan.furminger.bakersdozen;

import java.awt.Image;

public class Card {
	private String rank = "";
	private int suit = -1;
	private int value = 0;
	private Image image = null;
	private static int width = 0;
	private static int height = 0;
	private int x = 0;
	private int y = 0;
	
	public Card(String rank, int suit, int value, Image image) {
		this.rank = rank;
		this.suit = suit;
		this.value = value;
		this.image = image;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public String getRank() {
		return rank;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
