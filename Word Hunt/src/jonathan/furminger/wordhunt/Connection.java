package jonathan.furminger.wordhunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Connection implements Runnable {
	private static final String DEFAULT_NAME = "(New Client)";
	
	private WordHuntServer server;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Game game;
	private String name = DEFAULT_NAME;
	private int id = 0;
	
	public Connection(WordHuntServer server, Socket socket, Game game) {
		this.server = server;
		this.socket = socket;
		this.game = game;
		
		new Thread(this).start();
	}
	
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occured when connecting to a new client or communicating with that client");
		}
		finally {
			quit();
		}
	}
	
	public void quit() {
		server.log("The connection ended for " + name);
		try {
			socket.close();
		}
		catch (IOException e) {}
	}
	

}
