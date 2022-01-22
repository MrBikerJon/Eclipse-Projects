package jonathan.furminger.wizardofyesno;

import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WizardOfYesNo extends JFrame {
	
	private static final String[] ANSWER = {"Yes", "No"};

	
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
		
		setSize(200, 100);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		new WizardOfYesNo();
	}

}
