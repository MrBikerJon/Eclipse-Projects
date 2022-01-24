package jonathan.furminger.wizardofyesno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class WizardOfYesNo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final String[] ANSWER = {"Yes.", "Go for it!", "Sure!", "Good idea!", "No.", "I wouldn't", "Bad idea", "That's a big no no"};

	
	public WizardOfYesNo() {
		
		TitleLabel titleLabel = new TitleLabel("Wizard of Yes/No");
		add(titleLabel, BorderLayout.PAGE_START);
		
		Random rand = new Random();
		int numberOfAnswers = ANSWER.length;
		int pick = rand.nextInt(numberOfAnswers);
		String answer = ANSWER[pick];
		
		JLabel label = new JLabel();
		label.setText(answer);
		Font font = new Font(Font.SERIF, Font.BOLD, 32);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		if(pick > 5) {
			label.setBackground(Color.RED);
		} 
		else {
			label.setBackground(Color.GREEN);
		}
		
		add(label, BorderLayout.CENTER);
		
		String disclaimer = "This is only a suggestion. Use your own good judgment. The Wizard of Yes/No is not responsible for the consequences of your decisions.";
		
		// 		JTextArea disclaimerTextArea = new JTextArea(disclaimer, 3, 30);
		JTextArea disclaimerTextArea = new JTextArea(disclaimer);
		disclaimerTextArea.setLineWrap(true);
		disclaimerTextArea.setWrapStyleWord(true);
		disclaimerTextArea.setEditable(false);
		//add(disclaimerTextArea, BorderLayout.PAGE_END);
		
		JScrollPane scrollPane = new JScrollPane(disclaimerTextArea);
		
		Dimension size = new Dimension(0, 50);
		
		scrollPane.setPreferredSize(size);
		
		add(scrollPane, BorderLayout.PAGE_END);
		
		setTitle("Wizard of Yes/No");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		try {
			String className = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
			
		} catch (Exception e) {
			
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new WizardOfYesNo();
				
			}
		});
	}

}
