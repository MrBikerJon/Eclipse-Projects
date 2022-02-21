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
				else if(input.length() > 0) {
					String actionCode = input.substring(0, 1);
					String parameters = input.substring(1);
					switch(actionCode) {
					case ActionCode.NAME :
						String submittedName = parameters;
						boolean added = server.addConnection(this, submittedName);
						if(added) {
							validName = true;
							name = submittedName;
							sendToClient(ActionCode.ACCEPTED);
							String message = ActionCode.CHAT + name + " joined the conversation";
							server.broadcast(message);
						}
						else {
							sendToClient(ActionCode.REJECTED);
						}
						break;
					case ActionCode.QUIT :
						keepRunning = false;
						break;
					case ActionCode.BROADCAST :
						if(validName) {
							String message = ActionCode.CHAT + parameters;
							server.broadcast(message);
						}
					}
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
		if(!name.equals(DEFAULT_NAME)) {
			server.removeConnection(name);
			
			if(out != null) {
				String s = ActionCode.CHAT + name + " left the conversation";
				server.broadcast(s);
				out = null;
			}
		}
		try {
			socket.close();
		}
		catch (IOException e) {}
	}
	
	public void sendToClient(String s) {
		out.println(s);
		server.log("Sent to " + name + ": " + s);
	}
	
	public String getName() {
		return name;
	}
	
}
