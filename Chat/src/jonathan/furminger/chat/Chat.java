package jonathan.furminger.chat;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;
import jonathan.furminger.networking.LogInDialog;

public class Chat extends JFrame implements Runnable {


	private static final long serialVersionUID = 1L;
	private static final int PORT_NUMBER = 63458;
	
	private String name = "Jonathan";
	private String host = "localhost";
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private JTextArea chatArea = new JTextArea(20, 20);
	private JTextArea inputArea = new JTextArea(3, 20);
	private LogInDialog logInDialog = new LogInDialog("Chat");

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
		
		logIn();
		
		new Thread(this).start();
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Chat");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		
		// chat area
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		Insets marginInsets = new Insets(3, 3, 3, 3);
		chatArea.setMargin(marginInsets);
		JScrollPane chatScrollPane = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(chatScrollPane);
		
		// input area
		JLabel messageLabel = new JLabel("Type your message here");
		mainPanel.add(messageLabel);
		inputArea.setLineWrap(true);
		inputArea.setWrapStyleWord(true);
		inputArea.setMargin(marginInsets);
		inputArea.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					send();
				}
			}
		});
		JScrollPane inputScrollPane = new JScrollPane(inputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(inputScrollPane);
		
		// button panel
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.PAGE_END);
		JButton sendButton = new JButton("Send");
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send();
			}
		});
		buttonPanel.add(sendButton);
	}
	
	public void run() {
		try {
			socket = new Socket(host, PORT_NUMBER);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
			boolean keepRunning = true;
			while(keepRunning) {
				String input = in.readLine();
				if(input == null) {
					keepRunning = false;
				}
				else if(input.length() > 0) {
					String actionCode = input.substring(0, 1);
					String parameters = input.substring(1);
					switch(actionCode) {
					case ActionCode.SUBMIT :
						out.println(ActionCode.NAME + name);
						break;
					case ActionCode.ACCEPTED :
						setTitle(name);
						inputArea.requestFocus();
						break;
					case ActionCode.REJECTED :
						JOptionPane.showMessageDialog(this, "User name " + name + " is not available");
						logIn();
						out.println(ActionCode.NAME + name);
						break;
					case ActionCode.CHAT :
						Toolkit.getDefaultToolkit().beep();
						chatArea.append(parameters + "\n\n");
						// scroll the chat area
						String text = chatArea.getText();
						int endOfText = text.length();
						chatArea.setCaretPosition(endOfText);
					}
				}
			}
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
			if(out != null) {
				out.println(ActionCode.QUIT);
			}
			if(socket != null) {
				socket.close();
			}
		}
		catch (Exception e) {}
		System.exit(0);
	}
	
	private void send() {
		String message = inputArea.getText().trim();
		if(message.length() > 0) {
			inputArea.setText("");
			String s = ActionCode.BROADCAST + name + ": " + message;
			out.println(s);
		}
	}
	
	private void logIn() {
		logInDialog.setVisible(true);
		if(!logInDialog.isCanceled()) {
			host = logInDialog.getIpAddress();
			name = logInDialog.getUserName();
			System.out.println(host + "  " + name);
		}
		else {
			close();
		}
	}

}
