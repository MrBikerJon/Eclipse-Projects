package jonathan.furminger.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import jonathan.furminger.mycomponents.TitleLabel;

public class ChatServer extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final int PORT_NUMBER = 63458;
	
	private JTextArea logArea = new JTextArea(10, 30);
	private JButton startButton = new JButton("Start");
	private ServerSocket serverSocket;

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch(Exception e) {}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ChatServer();
			}
		});

	}
	
	public ChatServer() {
		initGUI();
		setTitle("Chat Server");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Chat Server");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		// log area
		logArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(logArea);
		mainPanel.add(scrollPane);
		DefaultCaret caret = (DefaultCaret)logArea.getCaret();
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
		
		// listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				stop();
				System.exit(0);
			}
		});
		
	}
	
	private void startServer() {
		startButton.setEnabled(false);
		 new Thread(this).start();
	}
	
	public void log(String message) {
		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy, HH:mm:ss");
		String timeStamp = dateFormat.format(time);
		logArea.append(timeStamp + " " + message + "\n");
	}
	
	public void run() {
		log("The server is running.");
		try {
			serverSocket = new ServerSocket(PORT_NUMBER);
			while(true) {
				Socket socket = serverSocket.accept();
				log("The server is starting a new connection");
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
	
	private void stop() {
		if(serverSocket != null && !serverSocket.isClosed()) {
			try {
				serverSocket.close();
			}
			catch (Exception e) {
				log("Unable to close the server connection.");
				log(e.getMessage());
			}
		}
	}

}
