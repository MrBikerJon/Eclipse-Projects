package jonathan.furminger.chat;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class Chat extends JFrame implements Runnable {


	private static final long serialVersionUID = 1L;
	private static final int PORT_NUMBER = 63458;
	
	private String name = "Jonathan";
	private String host = "localhost";
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Chat();
			}
		});

	}
	
	public Chat() {
		initGUI();
		setTitle("Chat");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		new Thread(this).start();
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Chat");
		add(titleLabel, BorderLayout.PAGE_START);
	}
	
	public void run() {
		try {
			socket = new Socket(host, PORT_NUMBER);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			String input = in.readLine();
		}
		catch (ConnectException e) {
			JOptionPane.showMessageDialog(this, "The server is not running");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this,  "Lost connection to the server");
		}
		finally {
			close();
		}
	}
	
	private void close() {
		try {
			if(socket != null) {
				socket.close();
			}
		}
		catch (Exception e) {}
		System.exit(0);
	}

}
