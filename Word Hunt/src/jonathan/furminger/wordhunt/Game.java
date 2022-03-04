package jonathan.furminger.wordhunt;

import java.util.ArrayList;

public class Game {
	private ArrayList<String> availableNames;
	private int playersReady = 0;
	private int maxNumberOfPlayers;
	
	public Game(ArrayList<String> availableNames) {
		this.availableNames = availableNames;
		maxNumberOfPlayers = availableNames.size();
	}
	
	public int getMaxNumberOfPlayers() {
		return maxNumberOfPlayers;
	}
}
