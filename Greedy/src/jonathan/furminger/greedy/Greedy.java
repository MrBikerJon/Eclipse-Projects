package jonathan.furminger.greedy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class Greedy extends JFrame {

	/**
	 * 
	 */
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
		
		// score panel
		
		// dice row panel
		
		// points panel
		
		// dice panel
		
		// high score panel
		
		// button panel
	}

}
