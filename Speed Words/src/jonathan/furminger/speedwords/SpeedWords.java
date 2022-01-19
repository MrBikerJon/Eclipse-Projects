package jonathan.furminger.speedwords;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.ScorePanel;
import jonathan.furminger.mycomponents.TitleLabel;

public class SpeedWords extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final Color TAN = new Color(222, 191, 168);
	private ScorePanel scorePanel = new ScorePanel(0, TAN);
	private SpeedWordsTimerPanel swTimerPanel = new SpeedWordsTimerPanel(this, 60);
	private static final Font LIST_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	private JTextArea textArea = new JTextArea();

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SpeedWords();
			}
		});
	}
	
	public SpeedWords() {
		initGUI();
		
		setTitle("Speed Words");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Speed Words");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.setBackground(TAN);
		add(mainPanel, BorderLayout.CENTER);
		
		// left panel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBackground(TAN);
		add(leftPanel);
		
		// score panel
		leftPanel.add(scorePanel);
		
		// timer panel
		JPanel timerPanel = new JPanel();
		timerPanel.setBackground(Color.RED);
		leftPanel.add(timerPanel);
		swTimerPanel.setBackground(Color.RED);
		timerPanel.add(swTimerPanel);
		
		// game panel
		
		// text area
		
	}
}
