package jonathan.furminger.dotsandboxes;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;
import jonathan.furminger.networking.LogInDialog;

public class DotsAndBoxes extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final int PORT_NUMBER = 51623;
	
	private LogInDialog logInDialog = new LogInDialog("Dots and Boxes");
	private String host = "";
	private String name = "";
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	private boolean keepRunning = true;
	private int rows;
	private int columns;
			
	public DotsAndBoxes() {
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
				new DotsAndBoxes();
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
				else if (input.length() > 1) {
					String actionCode = input.substring(0, 2);
					String parameters = input.substring(2);
					switch (actionCode) {
					case ActionCode.SUBMIT :
						out.println(ActionCode.NAME + name);
						break;
					case ActionCode.REJECTED :
						JOptionPane.showMessageDialog(this, "User name " + name + " is not available.");
						logIn();
						out.println(ActionCode.NAME + name);
						break;
					case ActionCode.ACCEPTED :
						rows = Integer.parseInt(parameters);
						columns = rows;
						openWindow();
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
	
	private void openWindow() {
		initGUI();
		setTitle(name);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Dots and Boxes");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}
	

}
