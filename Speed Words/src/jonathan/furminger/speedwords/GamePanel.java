package jonathan.furminger.speedwords;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
	private static final int HEIGHT = 300;
	private static final int START_X = (WIDTH / 2) - (7 * LetterTile.SIZE / 2);
	private static final int START_Y = (HEIGHT / 2) - (LetterTile.SIZE / 2);
	private static final String FILE_NAME = "/enable1_7.txt";
	
	private SpeedWords speedWords;
	private ArrayList<TileSet> tileSets = new ArrayList<TileSet>();
	private ArrayList<String> sevenLetterWords = new ArrayList<String>();
	private Random rand = new Random();
	
	public GamePanel(SpeedWords speedWords) {
		this.speedWords = speedWords;
	}

	public void paintComponent(Graphics g) {
		
		// draw background
		g.setColor(SpeedWords.TAN);
		g.fillRect(0, 0, WIDTH,  HEIGHT);
		
		TileSet tileSet = new TileSet("HELLO", 100, 100);
		tileSet.draw(g);
		
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	
}
