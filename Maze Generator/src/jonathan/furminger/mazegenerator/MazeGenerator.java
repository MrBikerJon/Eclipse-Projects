package jonathan.furminger.mazegenerator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class MazeGenerator extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private TitleLabel titleLabel = new TitleLabel("Maze");

	public static void main(String[] args) {
		String className = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MazeGenerator();
			}
		});
	}
	
	public MazeGenerator() {
		initGUI();
		setTitle("MazeGenerator");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	private void initGUI() {
		add(titleLabel, BorderLayout.PAGE_START);
		
		Cell cell = new Cell();
		add(cell, BorderLayout.CENTER);
	}

}
