package jonathan.furminger.dotsandboxes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Connection implements Runnable {

	private static final String DEFAULT_NAME = "(New Client)";
	
	private DotsAndBoxesServer server;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	private Game game;
	private String name = DEFAULT_NAME;
	private int id = 0;
	
	public Connection(DotsAndBoxesServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		new Thread(this).start();
	}
	
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
			sendToClient(ActionCode.SUBMIT);
			
			boolean keepRunning = true;
			
			while(keepRunning) {
				String input = in.readLine();
				server.log(input + " was received from " + name);
				if(input == null) {
					keepRunning = false;
				}
			}
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, "An error occurred when connecting to a new client or communicating with that client");
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
	
	public void sendToClient(String s) {
		out.println(s);
		server.log(s + " was sent to " + name);
	}
	
	public String getName() {
		return name;
	}
}
