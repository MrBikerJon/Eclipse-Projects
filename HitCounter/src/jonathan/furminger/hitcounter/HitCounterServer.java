package jonathan.furminger.hitcounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HitCounterServer {

	private static final int PORT_NUMBER = 63457;
	
	private ServerSocket serverSocket;
	private Socket socket;
	private int count = 0;
	
	public static void main(String[] args) {

		new HitCounterServer();
		
	}
	
	public HitCounterServer() {
		System.out.println("The server is running");
		try {
			serverSocket = new ServerSocket(PORT_NUMBER);
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println("Starting new connection.");
				PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);
				count++;
				toClient.println("" + count);
			}

		}
		catch(IOException e) {
			System.out.println("An exception was caught when trying to listen on port " + PORT_NUMBER + " or when listening for a connection.");
		}
		finally {
			System.out.println("The server stopped.");
			try {
				if(serverSocket != null && serverSocket.isClosed()) {
					serverSocket.close();
				}
				if(serverSocket != null) {
					socket.close();
				}
			}
			catch (Exception e) {}
		}
	}

}
