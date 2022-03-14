package jonathan.furminger.wordhunt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.networking.LogInDialog;
import jonathan.furminger.networking.Packet;

public class WordHunt extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private LogInDialog logInDialog = new LogInDialog("Word Hunt");
	private String host = "";
	private String name = "";
	
	private static final int PORT_NUMBER = 51593;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean keepRunning = true;
	
	public WordHunt() {
		logIn();
		new Thread(this).start();
	}
	
	public static void main(String[] args) {


		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new WordHunt();
			}
		});
	}
	
	private void logIn() {
		logInDialog.setVisible(true);
		if(!logInDialog.isCanceled()) {
			host = logInDialog.getIpAddress();
			name = logInDialog.getUserName();
		}
		else {
			close();
		}
	}
	
	public void run() {
		try {
			socket = new Socket(host, PORT_NUMBER);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			keepRunning = true;
			while(keepRunning) {
				String input = in.readLine();
				if(input == null) {
					keepRunning = false;
				}
				else if (input.length() > 0) {
					Packet packet = new Packet(input);
					String actionCode = packet.getActionCode();
					ArrayList<String> parameters = packet.getParameters();
					
					switch (actionCode) {
					case ActionCode.SUBMIT :
						packet = new Packet(ActionCode.NAME);
						packet.add(name);
						out.println(packet);
						break;
					}
				}
			}
		}
		catch (ConnectException e) {
			JOptionPane.showMessageDialog(this, "The server is not running");
		}

		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "The connection to the server was lost");
		}
		finally {
			close();
		}
	}
	
	public void close() {
		System.exit(0);
	}

}
