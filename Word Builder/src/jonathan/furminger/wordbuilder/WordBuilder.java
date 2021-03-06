package jonathan.furminger.wordbuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private final static String SETTINGS_FILE = "wordBuilderSettings.txt";
	
	private int windowX = -1;
	private int windowY = -1;
	private int windowH = -1;
	private int windowW = -1;
	private int windowState = JFrame.NORMAL;
	
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
		readSettings();
		if(windowX < 0) {
			pack();
			setLocationRelativeTo(null);
		}
		else {
			setLocation(windowX, windowY);
			setSize(windowW, windowH);
		}
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(windowState);
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
		
		Dimension boxSize = new Dimension(20, 0);
		Component box = Box.createRigidArea(boxSize);
		scorePanel.add(box);
		
		scoreTitleLabel.setFont(SMALLFONT);
		scorePanel.add(scoreTitleLabel);
		
		scoreLabel.setFont(BIGFONT);
		scorePanel.add(scoreLabel);
		
		// play panel
		playPanel.setLayout(new GridLayout(1, MAX));
		playPanel.setBackground(TAN);
		mainPanel.add(playPanel);
		for(int i = 0; i < MAX; i++) {
			LetterPanel letterPanel = new LetterPanel();
			letterPanel.setColumn(i);
			played[i] = letterPanel;
			playPanel.add(letterPanel);
		}
		
		mainPanel.add(Box.createGlue());
		
		// board panel
		boardPanel.setBackground(Color.BLACK);
		boardPanel.setLayout(new GridLayout(ROWS, COLS));
		int panelSize = played[0].getPanelSize();
		Dimension maxSize = new Dimension(COLS*panelSize, ROWS*panelSize);
		boardPanel.setMaximumSize(maxSize);
		
		mainPanel.add(boardPanel);
		
		BagOfLetters letters = new BagOfLetters();
		for (int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				LetterPanel letterPanel = letters.pickALetter();
				letterPanel.setColumn(col);
				board[row][col] = letterPanel;
				boardPanel.add(letterPanel);
			}
		}
		
		for(int col = 0; col < COLS; col++) {
			board[0][col].addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					LetterPanel letterPanel = (LetterPanel) e.getSource();
					click(letterPanel);
				}
			});
		}
		
		mainPanel.add(Box.createGlue());
		
		// button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
		
		acceptButton.setEnabled(false);
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accept();
			}
		});
		buttonPanel.add(acceptButton);
		
		undoButton.setEnabled(false);
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo();
			}
		});
		buttonPanel.add(undoButton);
		
		clearButton.setEnabled(false);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		buttonPanel.add(clearButton);
		
		JButton endButton = new JButton("End Game");
		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGame();
			}
		});
		buttonPanel.add(endButton);
	
	
		// listeners
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				resizeWindow();
			}
		});
		
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				resizeWindow();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				saveSettings();
			}
		});
	
	}
	
	private void click(LetterPanel letterPanel) {
		int wordLength = word.length();
		if(!letterPanel.isEmpty() && wordLength < MAX) {
			played[wordLength].copy(letterPanel);
			word += letterPanel.getLetter();
			points += letterPanel.getPoints();
			
			int col = letterPanel.getColumn();
			for(int row = 0; row < ROWS-1; row++) {
				board[row][col].copy(board[row+1][col]);
			}
			board[ROWS-1][col].setEmpty();
			
			updateButtonsAndPoints();
		}
	}
	
	private void updateButtonsAndPoints() {
		if (word.length() == 0) {
			acceptButton.setEnabled(false);
			undoButton.setEnabled(false);
			clearButton.setEnabled(false);
			pointsLabel.setText("0");
		}
		else if (word.length() < 3) {
			acceptButton.setEnabled(false);
			undoButton.setEnabled(true);
			clearButton.setEnabled(true);
			pointsLabel.setText("0");
		}
		else {
			if(dictionary.isAWord(word)) {
				acceptButton.setEnabled(true);
			}
			else {
				acceptButton.setEnabled(false);
			}
			undoButton.setEnabled(true);
			clearButton.setEnabled(true);
			int newPoints = points * word.length();
			pointsLabel.setText("" + newPoints);
		}
	}
	
	private void accept() {
		score += points * word.length();
		scoreLabel.setText("" + score);
		for(int i = 0; i < word.length(); i++) {
			played[i].setEmpty();
		}
		points = 0;word = "";
		updateButtonsAndPoints();
	}
	
	private void undo() {
		int last = word.length()-1;
		word = word.substring(0, last);
		LetterPanel lastPlayedPanel = played[last];
		points -= lastPlayedPanel.getPoints();
		int col = lastPlayedPanel.getColumn();
		for(int row = ROWS-1; row > 0; row--) {
			board[row][col].copy(board[row-1][col]);
		}
		board[0][col].copy(lastPlayedPanel);
		lastPlayedPanel.setEmpty();
		
		updateButtonsAndPoints();
	}
	
	private void clear() {
		int numberOfTimes = word.length();
		for(int i = 0; i < numberOfTimes; i++) {
			undo();
		}
	}
	
	private void endGame() {
		ArrayList<String> records = new ArrayList<String>();
		int index = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File (FILENAME)));
			String s = in.readLine();
			while(!(s == null)) {
				records.add(s);
				int indexOfBlank = s.indexOf(" ");
				String scoreString = s.substring(0, indexOfBlank);
				int oldScore = Integer.parseInt(scoreString);
				if(oldScore > score) {
					index++;
				}
				s = in.readLine();
			}
			in.close();
		}
		catch (FileNotFoundException e) {
			String message = FILENAME + " was not found. A new high score list will be created.";
			JOptionPane.showMessageDialog(this, message);
		}
		catch (IOException e) {
			String message = FILENAME + " could not be opened. A new high score list will be created.";
			JOptionPane.showMessageDialog(this, message);
		}
		catch (NumberFormatException e) {
			String message = FILENAME + " could not be read. A new high score list will be created";
			JOptionPane.showMessageDialog(this, message);
		}
		String message = "";
		if(index < 10) {
			message += "Your score of " + score + " is one of the top 10 highest scores!\n";
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String newRecord = score + " " + dateFormat.format(date);
			records.add(index, newRecord);
			if(records.size() > 10) {
				records.remove(10);
			}
			saveRecords(records);
		}
		
		message += "TOP 10 HIGH SCORES\n";
		for(int i = 0; i < records.size(); i++) {
			message += records.get(i) + "\n";
		}
		message += "Do you want to play again?";
		int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			newGame();
		}
		else {
			saveSettings();
			System.exit(0);
		}
		
	}
	
	private void saveRecords(ArrayList<String> records) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(FILENAME)));
			for (int i = 0; i < records.size(); i++) {
				out.write(records.get(i));
				out.newLine();
			}
			out.close();
		}
		catch (IOException e) {
			String message = "The high score records could not be saved";
			JOptionPane.showMessageDialog(this, message);
		}
	}
	
	private void newGame() {
		clear();
		
		BagOfLetters letters = new BagOfLetters();
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				LetterPanel letterPanel = letters.pickALetter();
				letterPanel.setColumn(col);
				board[row][col].copy(letterPanel);
			}
			score = 0;
			points = 0;
			word = "";
			scoreLabel.setText("0");
			updateButtonsAndPoints();
		}
	}
	
	private void resizeWindow() {
		int newWidth = mainPanel.getWidth();
		int newHeight = mainPanel.getHeight();
		int panelSize = newWidth/MAX;
		if(panelSize > newHeight/10) {
			panelSize = newHeight/10;
		}
		Dimension boardSize = new Dimension(panelSize*COLS, panelSize*ROWS);
		boardPanel.setMaximumSize(boardSize);
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				board[row][col].resize(panelSize);
			}
		}
		
		Dimension playSize = new Dimension(panelSize*MAX, panelSize);
		playPanel.setMaximumSize(playSize);
		for(int i = 0; i < played.length; i++) {
			played[i].resize(panelSize);
		}
		
		Font bigFont = new Font(Font.DIALOG, Font.BOLD, panelSize*3/4);
		Font smallFont = new Font(Font.DIALOG, Font.PLAIN, panelSize*3/10);
		pointsTitleLabel.setFont(smallFont);
		pointsLabel.setFont(bigFont);
		scoreTitleLabel.setFont(smallFont);
		scoreLabel.setFont(bigFont);
	}
	
	private void saveSettings() {
		
		Point location = getLocation();
		int x = location.x;
		int y = location.y;
		Dimension size = getSize();
		int w = size.width;
		int h = size.height;
		int state = getExtendedState();
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(SETTINGS_FILE)));
			out.write("" + x);
			out.newLine();
			out.write("" + y);
			out.newLine();
			out.write("" + w);
			out.newLine();
			out.write("" + h);
			out.newLine();
			out.write("" + state);
			
			out.close();
		}
		catch (IOException e) {
			String message = "Settings could not be saved.";
			JOptionPane.showMessageDialog(this, message);
		}

	}
	
	private void readSettings() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(SETTINGS_FILE)));
			String s = in.readLine();
			windowX = Integer.parseInt(s);
			s = in.readLine();
			windowY = Integer.parseInt(s);
			s = in.readLine();
			windowW = Integer.parseInt(s);
			s = in.readLine();
			windowH = Integer.parseInt(s);
			s = in.readLine();
			windowState = Integer.parseInt(s);
			
			in.close();
		}
		catch (FileNotFoundException e) {
		}
		catch (IOException e) {
		}
		catch (NumberFormatException e) {
		}

	}

}
