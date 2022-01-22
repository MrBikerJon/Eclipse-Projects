package jonathan.furminger.speedwords;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.ScorePanel;
import jonathan.furminger.mycomponents.TitleLabel;

public class SpeedWords extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final Color TAN = new Color(222, 191, 168);
	private static final Font LIST_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
	
	private ScorePanel scorePanel = new ScorePanel(0, TAN);
	private SpeedWordsTimerPanel swTimerPanel = new SpeedWordsTimerPanel(this, 60);
	private JTextArea textArea = new JTextArea();
	
	private GamePanel gamePanel = new GamePanel(this);

	
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
		swTimerPanel.start();
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
		mainPanel.add(leftPanel);
		
		// score panel
		leftPanel.add(scorePanel);
		
		// timer panel
		JPanel timerPanel = new JPanel();
		timerPanel.setBackground(Color.RED);
		leftPanel.add(timerPanel);
		
		swTimerPanel.setBackground(Color.RED);
		timerPanel.add(swTimerPanel);
		
		// game panel
		leftPanel.add(gamePanel);
		
		// text area
		Insets insets = new Insets(4, 10, 10, 4);
		textArea.setMargin(insets);
		textArea.setEditable(false);
		textArea.setFont(LIST_FONT);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		Dimension size = new Dimension(100, 0);
		scrollPane.setPreferredSize(size);
		mainPanel.add(scrollPane);
	}
	
	public void addToScore(int newPoints) {
		scorePanel.addToScore(newPoints);
	}
	
	public void setWordList(ArrayList<String> wordList) {
		String s = "";
		for(int i = 0; i < wordList.size(); i++) {
			String word = wordList.get(i);
			s = s + word + "\n";
		}
		textArea.setText(s);
	}
	
	public void outOfTime() {
		gamePanel.setOutOfTime(true);
		String message = "Time's up! Do you want to play again?";
		int option = JOptionPane.showConfirmDialog(this,  message, "Play Again?", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			textArea.setText("");
			scorePanel.reset();
			gamePanel.restart();
			swTimerPanel.setTime(60);
			swTimerPanel.start();
		}
		else {
			System.exit(0);
		}
	}
	
}
