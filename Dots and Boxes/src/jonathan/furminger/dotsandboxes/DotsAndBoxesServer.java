package jonathan.furminger.dotsandboxes;

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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class DotsAndBoxesServer extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private static final String FILE_NAME = "Settings.txt";
	private static final int PORT_NUMBER = 51623;
	
	private JTextField gridSizeField = new JTextField("8", 2);
	private JTextArea logArea = new JTextArea(10, 30);
	private JButton startButton = new JButton("Start");
	private int gridSize;
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
				new DotsAndBoxesServer();
			}
		});
	}
	
	public DotsAndBoxesServer() {
		initGUI();
		
		setTitle("Dots and Boxes Server");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(FILE_NAME)));
			String input = in.readLine();
			gridSize = Integer.parseInt(input);
			gridSizeField.setText(input);
			in.close();
		}
		catch (Exception e) {}
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Dots and Boxes Server");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				stop();
				System.exit(0);
			}
		});
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.PAGE_START);
		
		// option panel
		JPanel optionsPanel = new JPanel();
		mainPanel.add(optionsPanel);
		
		JLabel gridSizeLabel = new JLabel("Grid Size: ");
		optionsPanel.add(gridSizeLabel);
		
		gridSizeField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				JTextField field = (JTextField) e.getSource();
				field.selectAll();
			}
		});
		optionsPanel.add(gridSizeField);
		
		// log area
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea);
		mainPanel.add(scrollPane);
		DefaultCaret caret = (DefaultCaret) logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		// button panel
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.PAGE_END);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startServer();
			}
		});
		buttonPanel.add(startButton);
		
		getRootPane().setDefaultButton(startButton);
		
	}
	
	public void run() {
		log("The server is running.");
		
		try {
			serverSocket = new ServerSocket(PORT_NUMBER);
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				// start a new game for every two players
				if (game.getPlayersReady() > 1) {
					game = new Game(gridSize);
					log("Created a new game.");
				}
				
				new Connection(this, socket);
			}
		}
		catch (IOException e) {
			log("An exception was caught while trying to listen on port " + PORT_NUMBER);
			log(e.getMessage());
		}
		finally {
			try {
				if(!serverSocket.isClosed()) {
					serverSocket.close();
				}
			}
			catch (Exception e) {}
		}
	}

	private void startServer() {
		try {
			String s = gridSizeField.getText();
			int checkGridSize = Integer.parseInt(s);
			if(checkGridSize >= 3 && checkGridSize <= 20) {
				gridSize = checkGridSize;
				game = new Game(gridSize);
				
				new Thread(this).start();
				startButton.setEnabled(false);
				
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(new File(FILE_NAME)));
					out.write(s);
					out.close();
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(this, "An error was encountered when writing to " + FILE_NAME);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "The grid size must be between 3 and 20");
			}
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "The grid size is not a valid number");
		}
	}
	
	public void log(String message) {
		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss ");
		String timeStamp = dateFormat.format(time);
		logArea.append(timeStamp + message + "\n");
	}
	
	private void stop() {
		if(serverSocket != null && !serverSocket.isClosed()) {
			try {
				log("closing the server"); // remove later
				serverSocket.close();
			}
			catch (Exception e) {
				log("The server was unable to close the server connection");
				log(e.getMessage());
			}
		}
	}
	
	public synchronized Game addPlayerToGame (Connection connection, String name) {
		if(game.getPlayersReady() > 1) {
			game = new Game(gridSize);
			System.out.println("Created new game...");
			log("Created a new game");
		}
		
		if(!game.addPlayer(connection, name)) {
			return null;
		}
		else {
			return game;
		}
	}
	
}
