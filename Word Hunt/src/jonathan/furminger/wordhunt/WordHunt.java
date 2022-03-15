package jonathan.furminger.wordhunt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import jonathan.furminger.mycomponents.TitleLabel;
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
	
	private int numberOfPlayers = 0;
	private JPanel scoresPanel = new JPanel();
	private ScorePanel[] scorePanels;
	
	private JLabel messageLabel = new JLabel("Waiting for other players");
	
	private GamePanel gamePanel = new GamePanel(this);
	private JTextArea wordListArea = new JTextArea(); 
	
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
					case ActionCode.REJECTED :
						JOptionPane.showMessageDialog(this, "User name " + name + " was not invited to the game or is already being used by another player");
						logIn();
						packet = new Packet(ActionCode.NAME);
						packet.add(name);
						out.println(packet);
						break;
					case ActionCode.ACCEPTED :
						numberOfPlayers = Integer.parseInt(parameters.get(0));
						scorePanels = new ScorePanel[numberOfPlayers];
						for(int i = 0; i < numberOfPlayers; i++) {
							int playerNumber = i + 1;
							String playerName = "Player " + playerNumber;
							scorePanels[i] = new ScorePanel(0, Color.MAGENTA, playerName);
							scoresPanel.add(scorePanels[i]);
						}
						openWindow();
						break;
					case ActionCode.PLAYERS :
						for(int i = 0; i < parameters.size(); i++) {
							String name = parameters.get(i);
							scorePanels[i].setTitleLabel(name);
						}
						pack();
						break;
						
					case ActionCode.QUIT :
						String message = parameters.get(0) + " left the game";
						JOptionPane.showMessageDialog(this, message);
						close();
						break;
					case ActionCode.SHUT_DOWN :
						JOptionPane.showMessageDialog(this, "The server was shut down");
						close();
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
		keepRunning = false;
		try {
			if(out != null) {
				Packet packet = new Packet(ActionCode.QUIT);
				out.println(packet);
			}
			if(socket != null) {
				socket.close();
			}
		}
		catch (Exception e) {};
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
		TitleLabel titleLabel = new TitleLabel("Word Hunt");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.MAGENTA);
		add(mainPanel, BorderLayout.CENTER);
		
		// scores panel
		scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.X_AXIS));
		scoresPanel.setBackground(Color.MAGENTA);
		mainPanel.add(scoresPanel);
		
		// message
		messageLabel.setAlignmentX(CENTER_ALIGNMENT);
		messageLabel.setAlignmentY(CENTER_ALIGNMENT);
		messageLabel.setOpaque(true);
		messageLabel.setBackground(Color.WHITE);
		EmptyBorder messageBorder = new EmptyBorder(5, 10, 5, 10);
		messageLabel.setBorder(messageBorder);
		mainPanel.add(messageLabel);
		
		// horizontal panel
		JPanel horizontalPanel = new JPanel();
		horizontalPanel.setBackground(Color.MAGENTA);
		horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
		EmptyBorder gameBorder = new EmptyBorder(10, 10, 10, 10);
		horizontalPanel.setBorder(gameBorder);
		mainPanel.add(horizontalPanel);
		
		// game panel
		horizontalPanel.add(gamePanel);
	}

}
