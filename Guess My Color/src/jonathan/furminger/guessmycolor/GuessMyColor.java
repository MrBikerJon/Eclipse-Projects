package jonathan.furminger.guessmycolor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GuessMyColor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel samplePanel = new JPanel();
	private JPanel targetPanel = new JPanel();
	
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
		
	}
	
	private void generateTargetColor() {
		Color targetColor = new Color(50, 200, 150);
		targetPanel.setBackground(targetColor);
	}
	
}
