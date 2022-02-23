package jonathan.furminger.dotsandboxes;

public class Game {

	private int gridSize;
	private int playersReady = 0;
	
	public Game(int gridSize) {
		this.gridSize = gridSize;
	}
	
	public int getPlayersReady() {
		return playersReady;
	}
}
