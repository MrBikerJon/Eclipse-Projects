package jonathan.furminger.hitcounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HitCounter {
	
	private static final int PORT_NUMBER = 63457;
	
	private Socket socket;
	

	public static void main(String[] args) {
		new HitCounter();
	}
	
	public HitCounter() {
		try {
			socket = new Socket("168.168.1.254", PORT_NUMBER);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String answer = in.readLine();
			System.out.println("You are visitor number " + answer);
		}
		catch (IOException e) {
			System.out.println("An exception was caught.");
			System.out.println(e);
		}
		finally {
			try {
				if(socket != null) {
					socket.close();
				}
			}
			catch (Exception e) {}
		}
	}

}
