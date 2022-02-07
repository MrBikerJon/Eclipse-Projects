package jonathan.furminger.wordbuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jonathan.furminger.mycomponents.TitleLabel;

public class WordBuilder extends JFrame {

	public WordBuilder() {
		initGUI();
		setTitle("Word Builder");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		String className = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {};

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new WordBuilder();
			}
		});
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Word Builder");
		add(titleLabel, BorderLayout.PAGE_START);
		
		
		// main panel
		
		
		// score panel
		
		
		// play panel
		
		
		// board panel
		
		
		// button panel
		
		
		// listeners
	}

}
