package jonathan.furminger.wordbuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jonathan.furminger.mycomponents.TitleLabel;

public class WordBuilder extends JFrame {
	
	private static final int ROWS = 8;
	private static final int COLS = 12;
	private static final int MAX = 15;
	private static final String FILENAME = "highScores.txt";
	private static final Color TAN = new Color(222,191,168);
	private static final Font SMALLFONT = new Font(Font.DIALOG, Font.PLAIN, 12);
	private static final Font BIGFONT = new Font(Font.DIALOG, Font.BOLD, 30);
	
	private LetterPanel[][] board = new LetterPanel[ROWS][COLS];
	private LetterPanel[] played = new LetterPanel[MAX];
	private int points = 0;
	private int score = 0;
	private String word = "";
	private Dictionary dictionary = new Dictionary();

	private JPanel mainPanel = new JPanel();
	private JPanel boardPanel = new JPanel();
	private JPanel scorePanel = new JPanel();
	private JPanel playPanel = new JPanel();
	private JLabel pointsTitleLabel = new JLabel("Points: ");
	private JLabel scoreTitleLabel = new JLabel("Score: ");
	private JLabel pointsLabel = new JLabel("0");
	private JLabel scoreLabel = new JLabel("0");
	
	private JButton acceptButton = new JButton("Accept");
	private JButton undoButton = new JButton("Undo");
	private JButton clearButton = new JButton("Clear");

	public WordBuilder() {
		initGUI();
		setTitle("Word Builder");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		String className = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {};

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new WordBuilder();
			}
		});
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Word Builder");
		add(titleLabel, BorderLayout.PAGE_START);
		
		
		// main panel
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(TAN);
		add(mainPanel, BorderLayout.CENTER);

		// score panel
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
		scorePanel.setBackground(TAN);
		mainPanel.add(scorePanel);
		
		pointsTitleLabel.setFont(SMALLFONT);
		scorePanel.add(pointsTitleLabel);
		
		pointsLabel.setFont(BIGFONT);
		scorePanel.add(pointsLabel);
		
		scoreTitleLabel.setFont(SMALLFONT);
		scorePanel.add(scoreTitleLabel);
		
		scoreLabel.setFont(BIGFONT);
		scorePanel.add(scoreLabel);
		
		// play panel
		
		
		// board panel
		boardPanel.setBackground(Color.BLACK);
		boardPanel.setLayout(new GridLayout(ROWS, COLS));
		mainPanel.add(boardPanel);
		
		BagOfLetters letters = new BagOfLetters();
		for (int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				LetterPanel letterPanel = letters.pickALetter();
				board[row][col] = letterPanel;
				boardPanel.add(letterPanel);
			}
		}
		
		// button panel
		
		
		// listeners
	}

}
