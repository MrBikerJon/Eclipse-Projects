package jonathan.furminger.wizardofyesno;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class WizardOfYesNo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final String[] ANSWER = {"Yes.", "Go for it!", "Sure!", "Good idea!", "No.", "I wouldn't", "Bad idea", "That's a big no no"};

	
	public WizardOfYesNo() {
		Random rand = new Random();
		int numberOfAnswers = ANSWER.length;
		int pick = rand.nextInt(numberOfAnswers);
		String answer = ANSWER[pick];
		
		JLabel label = new JLabel();
		label.setText(answer);
		Font font = new Font(Font.SERIF, Font.BOLD, 32);
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
		
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
