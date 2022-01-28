package jonathan.furminger.slidingtiles;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import jonathan.furminger.mycomponents.TitleLabel;

public class SlidingTiles extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FILENAME = "slidingTilesImage.jpg";
	private int tileSize = 50;
	private int gridSize = 4;

	private BufferedImage image = null;

	public SlidingTiles() {
		try {
			image = ImageIO.read(new File(FILENAME));
		}
		catch (IOException e) {
			String message = "This image file " + FILENAME + " could not be opened";
			JOptionPane.showMessageDialog(this, message);
		}
		
		
		TileButton.setTileSizeAndMaxTiles(tileSize, gridSize * gridSize);
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
