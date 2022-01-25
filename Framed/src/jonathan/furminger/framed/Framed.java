package jonathan.furminger.framed;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class Framed extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int GRIDSIZE = 3;
	private LightButton[][] lightButton = new LightButton[GRIDSIZE][GRIDSIZE];

	public static void main(String[] args) {

		try {
			String className = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}
		catch (Exception e) {}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Framed();
				}
		});
	}
	
	public Framed() {
		initGUI();
		
		setTitle("Framed");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		newGame();
	}
	
	public void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Framed");
		add(titleLabel, BorderLayout.PAGE_START);
		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		
		for(int row = 0; row < GRIDSIZE; row++) {
			for(int col = 0; col < GRIDSIZE; col++) {
				lightButton[row][col] = new LightButton(row, col);
				lightButton[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LightButton button = (LightButton) e.getSource();
						int row = button.getRow();
						int col = button.getCol();
						toggleLights(row, col);
					}
			});
				centerPanel.add(lightButton[row][col]);
			}
		}
	}
	
	public void toggleLights(int row, int col) {
		lightButton[row][col].toggle();
		// top left corner
		if(row==0 && col==0) {
			lightButton[0][1].toggle();
			lightButton[1][1].toggle();
			lightButton[1][0].toggle();
		}
		//top right corner
		else if(row==0 && col==2) {
			lightButton[0][1].toggle();
			lightButton[1][1].toggle();
			lightButton[1][2].toggle();
		}
		// bottom left corner
		else if(row==2 && col==0) {
			lightButton[1][0].toggle();
			lightButton[1][1].toggle();
			lightButton[2][1].toggle();
		}
		// bottom right corner
		else if(row==2 && col==2) {
			lightButton[2][1].toggle();
			lightButton[1][1].toggle();
			lightButton[1][2].toggle();
		}
		// top row middle
		else if(row==0 && col==1) {
			lightButton[0][0].toggle();
			lightButton[0][2].toggle();
		}	
		// bottom row middle
		else if(row==2 && col==1) {
			lightButton[2][0].toggle();
			lightButton[2][2].toggle();
		}
		// left side middle
		else if(row==1 && col==0) {
			lightButton[0][0].toggle();
			lightButton[2][0].toggle();
		}
		// right side middle
		else if(row==1 && col==2) {
			lightButton[0][2].toggle();
			lightButton[2][2].toggle();
		}
		//center
		else if(row==1 && col==1) {
			lightButton[0][1].toggle();
			lightButton[1][0].toggle();
			lightButton[1][2].toggle();
			lightButton[2][1].toggle();
		}
	}
	
	private void newGame() {
		for(int row = 0; row < GRIDSIZE; row++) {
			for(int col = 0; col < GRIDSIZE; col++) {
				lightButton[row][col].turnOn();
			}
		}
		lightButton[1][1].toggle();
	}

}
