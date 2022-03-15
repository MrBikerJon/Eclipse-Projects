package jonathan.furminger.wordhunt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import jonathan.furminger.mycomponents.TitleLabel;

public class WordHuntServer extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private JTextField namesField = new JTextField("Player 1, Player 2");
	private JTextArea logArea = new JTextArea(10, 30);
	private JButton startStopButton = new JButton("Start");
	private ArrayList<String> names;
	private boolean listening = false;
	private static final String FILE_NAME = "PlayerNames.txt";
	private static final int PORT_NUMBER = 51593;
	private ServerSocket serverSocket;
	private Game game;
	

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new WordHuntServer();
			}
		});
	}
	
	public WordHuntServer() {
		initGUI();
		setTitle("Word Hunt Server");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(FILE_NAME)));
			String namesString = "";
			String name = in.readLine();
			while (name != null) {
				namesString += name + ",";
				name = in.readLine();
			}
			namesString = namesString.substring(0, namesString.length() - 1);
			namesField.setText(namesString);
			in.close();
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "The file " + FILE_NAME + " was not found");
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "An error was encountered reading from " + FILE_NAME);
		}
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Word Hunt Server");
		add(titleLabel, BorderLayout.PAGE_START);
		// listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				stopServer();
				System.exit(0);
			}
		});
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		// options panel
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		mainPanel.add(optionsPanel);
		JLabel namesLabel = new JLabel("Invited Players (separate names with commas):");
		optionsPanel.add(namesLabel);
		namesField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		optionsPanel.add(namesField);
		
		// log area
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea);
		mainPanel.add(scrollPane);
		DefaultCaret caret = (DefaultCaret) logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		// button panel
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.PAGE_END);
		startStopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startServer();
			}
		});
		buttonPanel.add(startStopButton);
		getRootPane().setDefaultButton(startStopButton);
	}
	
	public void run() {
		log("The server is running");
		try {
			serverSocket = new ServerSocket(PORT_NUMBER);
			while(listening) {
				Socket socket = serverSocket.accept();
				new Connection(this, socket, game);
			}
		}
		catch (IOException e) {
			// ignore the expected IOException thrown when the Stop button is clicked-when listening is true
			if(listening) {
				log("An exception was caught while trying to listen on port " + PORT_NUMBER);
				log(e.getMessage());
				stopServer();
			}
		}
	}
	
	private void startServer() {
		if(!listening) {
			String namesString = namesField.getText();
			StringTokenizer tokenizer = new StringTokenizer(namesString, ",");
			names = new ArrayList<String>();
			while(tokenizer.hasMoreTokens()) {
				String name = tokenizer.nextToken().trim();
				names.add(name);
			}
			if(names.size() > 0) {
				listening = true;
				
				game = new Game(names);
				log("A new game was created");
				
				new Thread(this).start();
				startStopButton.setText("Stop");
				
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(new File(FILE_NAME)));
					for(int i = 0; i < names.size(); i++) {
						String name = names.get(i);
						out.write(name);
						out.newLine();
					}
					out.close();
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(this, "An error was encountered when writing to " + FILE_NAME 
							+ " and the invited platers' names could not be saved");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "You must enter at least one name");
			}
		}
		else {
			stopServer();
		}
	}
	
	private void stopServer() {
		listening = false;
		startStopButton.setText("Start");
		log("The server was stopped");
		
		// stop listening for new clients
		if(serverSocket != null && !serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (Exception e) {
				log("An exception was caught when trying to stop the server connection");
				log(e.getMessage());
			}
		}
		
		// stop an existing game
		if(game != null) {
			game.shutDown();
			game = null;
		}
	}
	
	public void log(String message) {
		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy, HH:mm:ss");
		String timeStamp = dateFormat.format(time);
		logArea.append(timeStamp + message + "\n");
	}

}
