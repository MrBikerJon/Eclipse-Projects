package jonathan.furminger.catandmouse;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.ScorePanel;
import jonathan.furminger.mycomponents.TitleLabel;

public class CatAndMouse extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ScorePanel scorePanel = new ScorePanel(0, Color.YELLOW);
	private GamePanel gamePanel = new GamePanel(scorePanel);

	public static void main(String[] args) {

		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CatAndMouse();
			}
		});
	}

	public CatAndMouse() {
		initGUI();
		setTitle("Cat and Mouse");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Cat and Mouse");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.GREEN);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel, BorderLayout.CENTER);
		
		// score panel
		mainPanel.add(scorePanel);
		
		
		// game panel
		mainPanel.add(gamePanel);
		
	}
	
}
