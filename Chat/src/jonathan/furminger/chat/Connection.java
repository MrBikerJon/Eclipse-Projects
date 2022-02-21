package jonathan.furminger.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection implements Runnable {

	private static final String DEFAULT_NAME = "(New Client)";
	
	private ChatServer server;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String name = DEFAULT_NAME;
	
	public Connection(ChatServer server, Socket socket) {
		this.server = server;
		this.socket = socket;
		new Thread(this).start();
	}
	
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
			sendToClient(ActionCode.SUBMIT);
			boolean validName = false;
			
			boolean keepRunning = true;
			while(keepRunning) {
				String input = in.readLine();
				server.log(input + " was received from " + name);
				if(input == null) {
					keepRunning = false;
				}
			}
		}
		catch(IOException e) {
			server.log("An error occurred when connecting to a new client or communicating with that client");
			server.log(e.getMessage());
		}
		finally {
			quit();
		}
	}
	
	private void quit() {
		server.log("The connection ended for " + name);
		try {
			socket.close();
		}
		catch (IOException e) {}
	}
	
	public void sendToClient(String s) {
		out.print(s);
		server.log(s + " was sent to " + name);
	}
	
}
