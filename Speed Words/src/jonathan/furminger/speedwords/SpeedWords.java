package jonathan.furminger.speedwords;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class SpeedWords extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SpeedWords();
			}
		});
	}
	
	public SpeedWords() {
		initGUI();
		
		setTitle("Speed Words");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Speed Words");
		add(titleLabel, BorderLayout.PAGE_START);
	}
}
