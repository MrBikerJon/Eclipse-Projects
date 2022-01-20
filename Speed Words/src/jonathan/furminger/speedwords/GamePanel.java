package jonathan.furminger.speedwords;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import jonathan.furminger.mycommonmethods.FileIO;

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
	private TileSet movingTiles;
	private int mouseX;
	private int mouseY;
	
	public GamePanel(SpeedWords speedWords) {
		this.speedWords = speedWords;
		sevenLetterWords = FileIO.readTextFile(this, FILE_NAME);
		restart();
	}

	public void paintComponent(Graphics g) {
		
		// draw background
		g.setColor(SpeedWords.TAN);
		g.fillRect(0, 0, WIDTH,  HEIGHT);
		
		// draw all current tile sets
		for(int i = 0; i < tileSets.size(); i++) {
			TileSet tileSet = tileSets.get(i);
			tileSet.draw(g);
		}
		
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
	public void restart() {
		tileSets.clear();
		int range = sevenLetterWords.size();
		int choose = rand.nextInt(range);
		String s = sevenLetterWords.get(choose);
		TileSet tileSet = new TileSet(s, START_X, START_Y);
		tileSets.add(tileSet);
		repaint();
	}
	
	
}
