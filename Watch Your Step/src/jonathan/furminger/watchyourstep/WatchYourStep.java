package jonathan.furminger.watchyourstep;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class WatchYourStep extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int GRIDSIZE = 10;
	private static final int NUMBEROFHOLES = 10;
	private TerrainButton[][] terrain = new TerrainButton[GRIDSIZE][GRIDSIZE];
	private int totalRevealed = 0;

	public static void main(String[] args) {
		try {
		String className = UIManager.getCrossPlatformLookAndFeelClassName();
		UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				new WatchYourStep();
			}
		});
	}
	
	public WatchYourStep() {
		initGUI();
		setTitle("Watch Your Step");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Watch Your Step");
		add(titleLabel, BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		for(int row = 0; row < GRIDSIZE; row++) {
			for(int col = 0; col < GRIDSIZE; col++) {
				terrain[row][col] = new TerrainButton(row, col);
				centerPanel.add(terrain[row][col]);
			}
		}
		
	}

}
