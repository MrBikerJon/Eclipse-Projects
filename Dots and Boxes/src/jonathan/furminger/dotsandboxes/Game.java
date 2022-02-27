package jonathan.furminger.dotsandboxes;

import java.util.Random;

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
				
				if(playersReady == 2) {
					String message = ActionCode.OPPONENT + names[0];
					connections[1].sendToClient(message);
					message = ActionCode.OPPONENT + names[1];
					connections[0].sendToClient(message);
					
					Random rand = new Random();
					int whoStarts = rand.nextInt(2);
					connections[whoStarts].sendToClient(ActionCode.MY_TURN);
				}
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
	
	public void sendToOpponent(int playerId, String s) {
		if(playerId == 0) {
			if(connections[1] != null) {
				connections[1].sendToClient(s);
			}
		}
		else {
			if(connections[0] != null) {
				connections[0].sendToClient(s);
			}
		}
	}
}
