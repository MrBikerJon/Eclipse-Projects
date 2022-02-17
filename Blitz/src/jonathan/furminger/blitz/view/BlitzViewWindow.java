package jonathan.furminger.blitz.view;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import jonathan.furminger.blitz.controller.BlitzController;
import jonathan.furminger.mycomponents.TitleLabel;

public class BlitzViewWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private BlitzController controller;
	private GamePanel gamePanel;

	public BlitzViewWindow(BlitzController controller, BufferedImage cardBackImage) {
		this.controller = controller;
		gamePanel = new GamePanel(controller, cardBackImage);
		
		initGUI();
		setTitle("Blitz");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() {
		TitleLabel titleLabel = new TitleLabel("Blitz");
		add(titleLabel, BorderLayout.PAGE_START);
		
		// game panel
		add(gamePanel, BorderLayout.CENTER);
		
		// button panel
		
		
		
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
}
