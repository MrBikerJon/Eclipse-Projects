package jonathan.furminger.babybird;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.ScorePanel;
import jonathan.furminger.mycomponents.TitleLabel;

public class BabyBird extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int LIVES = 4;
	
	private ScorePanel scorePanel = new ScorePanel(0, Color.GREEN);
	private FlightPanel flightPanel = new FlightPanel(this);
	private BirdNestPanel birdNestPanel;

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BabyBird();
			}
		});

	}
	
	public BabyBird() {
		initGUI();
		setTitle("Baby Bird");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Baby Bird");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.GREEN);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		// score panel
		mainPanel.add(scorePanel);
		
		// flight panel
		mainPanel.add(flightPanel);
		
		// bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.BLACK);
		add(bottomPanel, BorderLayout.PAGE_END);
		
		// bird nest panel
		Bird bird = flightPanel.getBird();
		BufferedImage birdImage = bird.getImage();
		birdNestPanel = new BirdNestPanel(birdImage, LIVES - 1);
		bottomPanel.add(birdNestPanel);
		
	}
	
	public void nextBird() {
		int birdsRemaining = birdNestPanel.removeBird();
		if(birdsRemaining < 0) {
			String message = "You have no birds left. Do you want to play again?";
			int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				birdNestPanel.setBirdCount(LIVES - 1);
				scorePanel.reset();
				flightPanel.restart();
			}
			else {
				System.exit(0);
			}
		}
	}
	
	public void addToScore(int points) {
		scorePanel.addToScore(points);
	}

}
