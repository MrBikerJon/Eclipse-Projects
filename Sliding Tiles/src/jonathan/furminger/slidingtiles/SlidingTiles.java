package jonathan.furminger.slidingtiles;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class SlidingTiles extends JFrame {

	private static final long serialVersionUID = 1L;


	public SlidingTiles() {
		initGUI();
		setTitle("Sliding Tiles");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {

		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		} catch (Exception e) { }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SlidingTiles();
			}
		});
		

	}
	
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Sliding Tiles");
		add(titleLabel, BorderLayout.LINE_START);
		
	}

}
