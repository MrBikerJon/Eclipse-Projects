package jonathan.furminger.chat;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class ChatServer extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextArea logArea = new JTextArea(10, 30);
	private JButton startButton = new JButton("Start");

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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Chat Server");
		add(titleLabel, BorderLayout.CENTER);
	}

}
