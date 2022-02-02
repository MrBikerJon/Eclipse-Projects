package jonathan.furminger.greedy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class Greedy extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//instance variables
	private int points = 0;
	private int newPoints = 0;
	private int score = 0;
	private int round = 1;
	
	private JLabel pointsLabel = new JLabel("0");
	private JLabel scoreLabel = new JLabel("0");
	private JLabel roundLabel = new JLabel("1");
	
	private Font smallFont = new Font(Font.DIALOG, Font.PLAIN, 12);
	private Font bigFont = new Font(Font.DIALOG, Font.BOLD, 32);
	
	private JButton rollButton = new JButton("Roll");
	
	private Die[] dice = new Die[6];
	
	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Greedy();
			}
		});

	}
	
	public Greedy() {
		initGUI();
		setTitle("Greedy");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Greedy");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// main panel
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBackground(Color.GREEN);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		// score panel
		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(Color.GREEN);
		mainPanel.add(scorePanel);
		
		JLabel roundTitleLabel = new JLabel("Round: ");
		roundTitleLabel.setFont(smallFont);
		scorePanel.add(roundTitleLabel);
		roundLabel.setFont(bigFont);
		scorePanel.add(roundLabel);
		
		JLabel scoreTitleLabel = new JLabel("Score: ");
		scoreTitleLabel.setFont(smallFont);
		scorePanel.add(scoreTitleLabel);
		scoreLabel.setFont(bigFont);
		scorePanel.add(scoreLabel);
		
		// dice row panel
		JPanel diceRowPanel = new JPanel();
		diceRowPanel.setBackground(Color.GREEN);
		mainPanel.add(diceRowPanel);
		
		
		// points panel
		JPanel pointsPanel = new JPanel();
		pointsPanel.setBackground(Color.GREEN);
		pointsPanel.setLayout(new BoxLayout(pointsPanel, BoxLayout.Y_AXIS));
		Dimension size = new Dimension(70, 70);
		pointsPanel.setPreferredSize(size);
		diceRowPanel.add(pointsPanel);
		
		JLabel pointsTitleLabel = new JLabel("Points: ");
		pointsTitleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		pointsTitleLabel.setFont(smallFont);
		pointsPanel.add(pointsTitleLabel);
		
		pointsLabel.setFont(bigFont);
		pointsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		pointsPanel.add(pointsLabel);
		
		// dice panel
		JPanel dicePanel = new JPanel();
		dicePanel.setBackground(Color.GREEN);
		diceRowPanel.add(dicePanel);
		
		for(int i = 0; i < 6; i++) {
			dice[i] = new Die();
			dice[i].addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					clickedDie();
				}
			});
			dicePanel.add(dice[i]);
		}
		
		// high score panel
		
		// button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		rollButton.setEnabled(false);
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePoints();
				rollRemainingDice();
				rollButton.setEnabled(false);
			}
		});
		
		buttonPanel.add(rollButton);
		JButton endRoundButton = new JButton("End Round");
		buttonPanel.add(endRoundButton);
	}
	
	private boolean isValidSelection() {
		int[] count = {0, 0, 0, 0, 0, 0};
		int totalCount = 0;
		boolean valid = true;
		newPoints = 0;
		
		for(int i = 0; i < count.length; i++) {
			if(dice[i].isSelected()) {
				int value = dice[i].getValue();
				count[value-1]++;
				totalCount++;
			}
		}
		if(totalCount == 0) {
			valid = false;
		}
		else if (count[0] == 1 && count[1] == 1
				&& count[2] ==1 && count[3] == 1
				&& count[4] == 1 && count[5] == 1) {
					newPoints += 250;
		}
		else {
			for(int i = 0; i < count.length; i++) {
				switch(count[i]) {
				case 1:
					if(i == 0) {
						newPoints += 10;
					}
					else if (i == 4) {
						newPoints += 5;
					}
					else {
						valid = false;
					}
					break;
				case 2:
					if(i == 0) {
						newPoints += 20;
					}
					else if(i == 4) {
						newPoints += 10;
					}
					else {
						valid = false;
					}
					break;
				case 3:
					if(i == 0) {
						newPoints += 100;
					}
					else {
						newPoints += (i + 1) * 10;
					}
					break;
				case 4:
					newPoints += 200;
					break;
				case 5:
					newPoints += 300;
					break;
				case 6:
					newPoints += 500;
					break;
				}
			}
		}
			
		return valid;
	}
	
	public void clickedDie() {
		if(isValidSelection()) {
			rollButton.setEnabled(true);
		}
		else {
			rollButton.setEnabled(false);
		}
		
		pointsLabel.setText(""+ (points + newPoints));
	}
	
	private void updatePoints() {
		points += newPoints;
		pointsLabel.setText("" + points);
		newPoints = 0;
	}
	
	private void rollRemainingDice() {
		int count = 0;
		for(int i = 0; i < dice.length; i++) {
			if(dice[i].isSelected()) {
				dice[i].hold();
			}
			else if(! dice[i].isSelected()) {
				dice[i].roll();
				count++;
			}
		}
		
		if(count == 0) {
			rollAllDice();
		}
	}
	
	private void rollAllDice() {
		for(int i = 0; i < dice.length; i++) {
			dice[i].makeAvailable();
			dice[i].roll();
		}
		rollButton.setEnabled(false);
	}

}