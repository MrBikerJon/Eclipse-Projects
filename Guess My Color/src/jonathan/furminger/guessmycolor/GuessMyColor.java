package jonathan.furminger.guessmycolor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class GuessMyColor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel samplePanel = new JPanel();
	private JPanel targetPanel = new JPanel();
	
	private int targetRed = 0;
	private int targetGreen = 0;
	private int targetBlue = 0;
	
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	
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
		TitleLabel titleLabel = new TitleLabel("Guess My Color");
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
		
		Font font = new Font(Font.DIALOG, Font.BOLD, 18);
		
		JButton moreRedButton = new JButton("+");
		moreRedButton.setBackground(Color.RED);
		moreRedButton.setFont(font);
		moreRedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				increaseRed();
			}
		});
		buttonPanel.add(moreRedButton);
		
		JButton lessRedButton = new JButton("-");
		lessRedButton.setBackground(Color.RED);
		lessRedButton.setFont(font);
		lessRedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				decreaseRed();
			}
		});
		buttonPanel.add(lessRedButton);
		
		JButton moreGreenButton = new JButton("+");
		moreGreenButton.setBackground(Color.GREEN);
		moreGreenButton.setFont(font);
		moreGreenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				increaseGreen();
			}
		});
		buttonPanel.add(moreGreenButton);
		
		JButton lessGreenButton = new JButton("-");
		lessGreenButton.setBackground(Color.GREEN);
		lessGreenButton.setFont(font);
		lessGreenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				decreaseGreen();
			}
		});
		buttonPanel.add(lessGreenButton);
		
		JButton moreBlueButton = new JButton("+");
		moreBlueButton.setBackground(Color.BLUE);
		moreBlueButton.setFont(font);
		moreBlueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				increaseBlue();
			}
		});
		buttonPanel.add(moreBlueButton);
		
		JButton lessBlueButton = new JButton("-");
		lessBlueButton.setBackground(Color.BLUE);
		lessBlueButton.setFont(font);
		lessBlueButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e) {
				decreaseBlue();
			}
		});
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
	
	private void updateColorSample() {
		Color color = new Color(red, green, blue);
		samplePanel.setBackground(color);
		if(red == targetRed 
				&& green == targetGreen 
				&& blue == targetBlue) {
			String message = "Congratulations! The color values were, Red: " + red + ", Green: " + green + ", Blue: " + blue;
			JOptionPane.showMessageDialog(this,  message);
		}
	}
	
	private void increaseRed() {
		if(red <= 240) {
			red += 15;
			updateColorSample();
		}
	}
	
	private void decreaseRed() {
		if(red >= 15) {
			red -= 15;
			updateColorSample();
		}
	}
	
	private void increaseGreen() {
		if(green <= 240) {
			green += 15;
			updateColorSample();
		}
	}
	
	private void decreaseGreen() {
		if(green >= 15) {
			green -= 15;
			updateColorSample();
		}
	}
	
	private void increaseBlue() {
		if(blue <= 240) {
			blue += 15;
			updateColorSample();
		}
	}
	
	private void decreaseBlue() {
		if(blue >= 15) {
			blue -= 15;
			updateColorSample();
		}
	}
	
}
