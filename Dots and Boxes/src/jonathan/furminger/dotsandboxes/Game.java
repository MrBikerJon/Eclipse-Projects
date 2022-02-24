package jonathan.furminger.dotsandboxes;

public class Game {

	private int gridSize;
	private int playersReady = 0;
	private String[] names = {"", ""};
	private Connection[] connections = new Connection[2];
	
	public Game(int gridSize) {
		this.gridSize = gridSize;
	}
	
	public int getPlayersReady() {
		return playersReady;
	}
	
	public boolean addPlayer (Connection connection, String name) {
		boolean added = false;
		
		synchronized(names) {
			if(!name.equals(names[0]) && !name.equals(names[1])) {
				connections[playersReady] = connection;
				names[playersReady] = name;
				playersReady++;
				connection.sendToClient(ActionCode.ACCEPTED + gridSize);
				added = true;
			}
		}
		
		return added;
	}
	
	public void removePlayer(int playerId) {
		synchronized(names) {
			names[playerId] = "";
			connections[playerId] = null;
			playersReady--;
		}
	}
	
	public int getId (String name) {
		int id = -1;
		if(name.equals(names[0])) {
			id = 0;
		}
		else if (name.equals(names[1])) {
			id = 1;
		}
		return id;
	}
}
