package jonathan.furminger.gravitywell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.ScorePanel;
import jonathan.furminger.mycomponents.TitleLabel;

public class GravityWell extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ScorePanel scorePanel = new ScorePanel(0, Color.CYAN);
	private GamePanel gamePanel = new GamePanel(scorePanel);

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GravityWell();
			}
		});

	}
	
	public GravityWell() {
		initGUI();
		setTitle("Gravity Well");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Gravity Well");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.CYAN);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		
		// score panel
		mainPanel.add(scorePanel);
		
		// game panel
		mainPanel.add(gamePanel);
	}

}
