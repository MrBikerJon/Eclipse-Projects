package jonathan.furminger.greedy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

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
	
	private JLabel pointsLabel = new JLabel();
	private JLabel scoreLabel = new JLabel();
	private JLabel roundLabel = new JLabel();
	
	private Font smallFont = new Font(Font.DIALOG, Font.PLAIN, 12);
	private Font bigFont = new Font(Font.DIALOG, Font.BOLD, 36);
	
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
		

		
		// score panel
		
		// dice row panel
		
		// points panel
		
		// dice panel
		
		// high score panel
		
		// button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		add(buttonPanel, BorderLayout.PAGE_END);
		buttonPanel.add(rollButton);
		JButton endRoundButton = new JButton("End Round");
		buttonPanel.add(endRoundButton);
	}

}