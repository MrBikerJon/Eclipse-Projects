package jonathan.furminger.guessmycolor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GuessMyColor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel samplePanel = new JPanel();
	private JPanel targetPanel = new JPanel();
	
	private int targetRed = 0;
	private int targetGreen = 0;
	private int targetBlue = 0;
	
	public GuessMyColor() {
		initGUI();
		setTitle("Guess My Color");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		generateTargetColor();
	}
	
	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
			catch (Exception e) {
			
		}
		
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				new GuessMyColor();
			}
		});
	}

	private void initGUI() {
		JLabel titleLabel = new JLabel();
		Font titleFont = new Font(Font.SERIF, Font.BOLD, 32);
		titleLabel.setFont(titleFont);
		titleLabel.setHorizontalTextPosition(JLabel.CENTER);
		titleLabel.setText("Guess My Color");
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setOpaque(true);
		add(titleLabel, BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		add(centerPanel, BorderLayout.CENTER);
		
		Dimension size = new Dimension(50, 50);
		
		samplePanel.setBackground(Color.BLACK);
		samplePanel.setPreferredSize(size);
		centerPanel.add(samplePanel);
		
		targetPanel.setBackground(Color.CYAN);
		targetPanel.setPreferredSize(size);
		centerPanel.add(targetPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		add(buttonPanel, BorderLayout.PAGE_END);
		
		JButton moreRedButton = new JButton("+");
		moreRedButton.setBackground(Color.RED);
		buttonPanel.add(moreRedButton);
		
		JButton lessRedButton = new JButton("-");
		lessRedButton.setBackground(Color.RED);
		buttonPanel.add(lessRedButton);
		
		JButton moreGreenButton = new JButton("+");
		moreGreenButton.setBackground(Color.GREEN);
		buttonPanel.add(moreGreenButton);
		
		JButton lessGreenButton = new JButton("-");
		lessGreenButton.setBackground(Color.GREEN);
		buttonPanel.add(lessGreenButton);
		
		JButton moreBlueButton = new JButton("+");
		moreBlueButton.setBackground(Color.BLUE);
		buttonPanel.add(moreBlueButton);
		
		JButton lessBlueButton = new JButton("-");
		lessBlueButton.setBackground(Color.BLUE);
		buttonPanel.add(lessBlueButton);
		
		
	}
	
	private void generateTargetColor() {
		Random rand = new Random();
		targetRed = rand.nextInt(18) * 15;
		targetBlue = rand.nextInt(18) * 15;
		targetGreen = rand.nextInt(18) * 15;
		
		Color targetColor = new Color(targetRed, targetGreen, targetBlue);
		targetPanel.setBackground(targetColor);
	}
	
}
