package jonathan.furminger.mazegenerator;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import jonathan.furminger.mycomponents.TitleLabel;

public class MazeGenerator extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private TitleLabel titleLabel = new TitleLabel("Maze");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	}

}
