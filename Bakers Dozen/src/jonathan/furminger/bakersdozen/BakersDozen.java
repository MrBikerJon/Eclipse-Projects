package jonathan.furminger.bakersdozen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import jonathan.furminger.mycomponents.TitleLabel;

public class BakersDozen extends JFrame {

	private static final long serialVersionUID = 1L;

	public BakersDozen() {
		initGUI();
		setTitle("Baker's Dozen");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		String className = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(className);
		} 
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new BakersDozen();
			}
		});
			
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Baker's Dozen");
		
	}

}
