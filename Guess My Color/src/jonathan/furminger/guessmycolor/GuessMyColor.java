package jonathan.furminger.guessmycolor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GuessMyColor extends JFrame {

	private static final long serialVersionUID = 1L;

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

	public GuessMyColor() {
		initGUI();
		setTitle("Guess My Color");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		JLabel titleLabel = new JLabel();
		Font titleFont = new Font(Font.SERIF, Font.BOLD, 32);
		titleLabel.setFont(titleFont);
		titleLabel.setHorizontalTextPosition(JLabel.CENTER);
		titleLabel.setText("Guess My Color");
		add(titleLabel, BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.BLUE);
		add(centerPanel, BorderLayout.CENTER);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.RED);
		add(leftPanel, BorderLayout.LINE_START);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.GREEN);
		add(rightPanel, BorderLayout.LINE_END);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.YELLOW);
		add(bottomPanel, BorderLayout.PAGE_END);
		
	}
	
}
